package com.otakumap.domain.place_like.service;

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
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Pageable pageable = PageRequest.of(0, limit+1);
        Page<PlaceLike> placeLikePage = placeLikeRepository.findByUserIdAndIdLessThanOrderByIdDesc(userId, lastId, pageable);

        List<PlaceLikeResponseDTO.PlaceLikePreViewDTO> placeLikeDTOs = placeLikePage.getContent().stream()
                .map(placeLike -> new PlaceLikeResponseDTO.PlaceLikePreViewDTO(
                        placeLike.getId(),
                        placeLike.getUser().getId(),
                        placeLike.getPlace().getId(),
                        placeLike.getIsFavorite()
                ))
                .collect(Collectors.toList());

        boolean hasNext = placeLikePage.hasNext();
        Long newLastId = placeLikeDTOs.isEmpty() ? null : placeLikeDTOs.get(placeLikeDTOs.size() - 1).getId();

        return new PlaceLikeResponseDTO.PlaceLikePreViewListDTO(placeLikeDTOs, hasNext, newLastId);
    }



    private PlaceLikeResponseDTO.PlaceLikePreViewListDTO createPlaceLikePreviewListDTO(User user, Page<PlaceLike> placeLikePage) {
        List<PlaceLikeResponseDTO.PlaceLikePreViewDTO> list = placeLikePage.getContent().stream()
                .map(PlaceLikeConverter::placeLikePreViewDTO)
                .collect(Collectors.toList());

        boolean hasNext = placeLikePage.hasNext();
        Long lastId = list.isEmpty() ? null : list.get(list.size() - 1).getId();

        return PlaceLikeConverter.placeLikePreViewListDTO(list, hasNext, lastId);
    }


    @Override
    public boolean isPlaceLikeExist(Long id) {
        return placeLikeRepository.existsById(id);
    }
}