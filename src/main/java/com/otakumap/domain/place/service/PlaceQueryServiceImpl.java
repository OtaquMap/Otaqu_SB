package com.otakumap.domain.place.service;

import com.otakumap.domain.mapping.PlaceAnimation;
import com.otakumap.domain.place.DTO.PlaceResponseDTO;
import com.otakumap.domain.place.entity.Place;
import com.otakumap.domain.place_animation.repository.PlaceAnimationRepository;
import com.otakumap.domain.route_item.repository.RouteItemRepository;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.PlaceHandler;
import jakarta.transaction.Transactional;
import com.otakumap.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlaceQueryServiceImpl implements PlaceQueryService {
    private final PlaceRepository placeRepository;
    private final PlaceAnimationRepository placeAnimationRepository;
    private final RouteItemRepository routeItemRepository;

    @Override
    public boolean isPlaceExist(Long placeId) {
        return placeRepository.existsById(placeId);
    }

    @Override
    @Transactional
    public List<PlaceAnimation> getPlaceAnimations(Long placeId) {
        return placeAnimationRepository.findByPlaceId(placeId);
    }

    @Override
    @Transactional
    public PlaceResponseDTO.PlaceDetailDTO getPlaceDetail(Long routeId, Long placeId) {
        // RouteItem을 통해 Place 조회
        Place place = routeItemRepository.findPlaceByRouteIdAndPlaceId(routeId, placeId)
                .orElseThrow(() -> new PlaceHandler(ErrorStatus.PLACE_NOT_FOUND));

        // 애니메이션 관련 정보 조회
        List<PlaceAnimation> placeAnimations = placeAnimationRepository.findByPlaceId(placeId);
        List<String> animeNames = placeAnimations.stream()
                .map(placeAnimation -> placeAnimation.getAnimation().getName())
                .collect(Collectors.toList());

        // 해시태그 정보 조회
        List<String> hashtags = placeAnimations.stream()
                .flatMap(placeAnimation -> placeAnimation.getPlaceAnimationHashTags().stream())
                .map(placeAnimationHashTag -> placeAnimationHashTag.getHashTag().getName())
                .collect(Collectors.toList());

        return PlaceResponseDTO.PlaceDetailDTO.builder()
                .id(place.getId())
                .name(place.getName())
                .isSelected(Boolean.TRUE)  // isSelected는 True로 가정
                .latitude(place.getLat())
                .longitude(place.getLng())
                .animeName(animeNames)
                .hashtags(hashtags)
                .build();
    }
}
