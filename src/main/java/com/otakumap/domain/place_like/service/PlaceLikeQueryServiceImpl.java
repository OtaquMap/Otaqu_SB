package com.otakumap.domain.place_like.service;

import com.otakumap.domain.event_like.entity.EventLike;
import com.otakumap.domain.place_like.converter.PlaceLikeConverter;
import com.otakumap.domain.place_like.dto.PlaceLikeResponseDTO;
import com.otakumap.domain.place_like.entity.PlaceLike;
import com.otakumap.domain.place_like.repository.PlaceLikeRepository;
import com.otakumap.domain.user.entity.User;
import com.otakumap.domain.user.repository.UserRepository;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.EventHandler;
import com.otakumap.global.apiPayload.exception.handler.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaceLikeQueryServiceImpl implements PlaceLikeQueryService {
    private final PlaceLikeRepository placeLikeRepository;
    private final UserRepository userRepository;

    @Override
    public PlaceLikeResponseDTO.PlaceLikePreViewListDTO getPlaceLikeList(Long userId, Long lastId, int limit) {

        List<PlaceLike> result;
        Pageable pageable = PageRequest.of(0, limit+1);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Page<PlaceLike> placeLikePage = placeLikeRepository.findByUserIdAndIdLessThanOrderByIdDesc(userId, lastId, pageable);

        if (lastId.equals(0L)) {
            // lastId가 0일 경우: 처음부터 데이터를 조회
            result = placeLikeRepository.findAllByUserIsOrderByCreatedAtDesc(user, pageable).getContent();
        } else {
            // lastId가 0이 아닌 경우: lastId를 기준으로 이전 데이터를 조회
            PlaceLike placeLike = placeLikeRepository.findById(lastId).orElseThrow(() -> new EventHandler(ErrorStatus.PLACE_LIKE_NOT_FOUND));
            result = placeLikeRepository.findAllByUserIsAndCreatedAtLessThanOrderByCreatedAtDesc(user, placeLike.getCreatedAt(), pageable).getContent();
        }
        return createPlaceLikePreviewListDTO(user, result, limit);
    }

    private PlaceLikeResponseDTO.PlaceLikePreViewListDTO createPlaceLikePreviewListDTO(User user, List<PlaceLike> placeLikes, int limit) {
        boolean hasNext = placeLikes.size() > limit;
        Long lastId = null;

        if (hasNext) {
            placeLikes = placeLikes.subList(0, placeLikes.size() - 1);
            lastId = placeLikes.get(placeLikes.size() - 1).getId();
        }

        List<PlaceLikeResponseDTO.PlaceLikePreViewDTO> list = placeLikes
                .stream()
                .map(PlaceLikeConverter::placeLikePreViewDTO)
                .collect(Collectors.toList());

        return PlaceLikeConverter.placeLikePreViewListDTO(list, hasNext, lastId);
    }


    @Override
    public boolean isPlaceLikeExist(Long id) {
        return placeLikeRepository.existsById(id);
    }
}