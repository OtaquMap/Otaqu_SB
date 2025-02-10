package com.otakumap.domain.place.service;

import com.otakumap.domain.mapping.PlaceAnimation;
import com.otakumap.domain.place.DTO.PlaceResponseDTO;
import com.otakumap.domain.place.entity.Place;
import com.otakumap.domain.place_animation.repository.PlaceAnimationRepository;
import com.otakumap.domain.place_like.repository.PlaceLikeRepository;
import com.otakumap.domain.route.repository.RouteRepository;
import com.otakumap.domain.route_item.repository.RouteItemRepository;
import com.otakumap.domain.user.entity.User;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.PlaceHandler;
import com.otakumap.global.apiPayload.exception.handler.RouteHandler;
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
    private final PlaceLikeRepository placeLikeRepository;
    private final RouteRepository routeRepository;

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
    public PlaceResponseDTO.PlaceDetailDTO getPlaceDetail(User user, Long routeId, Long placeId) {
        // routeId가 존재하는지 먼저 확인
        boolean routeExists = routeRepository.existsById(routeId);
        if (!routeExists) {
            throw new RouteHandler(ErrorStatus.ROUTE_NOT_FOUND);
        }

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


        // 특정 사용자가 해당 Place를 좋아요 했는지 여부 확인
        boolean isLiked = placeLikeRepository.findByPlaceIdAndUserId(placeId, user.getId()).isPresent();

        return PlaceResponseDTO.PlaceDetailDTO.builder()
                .id(place.getId())
                .name(place.getName())
                .latitude(place.getLat())
                .longitude(place.getLng())
                .isFavorite(place.getIsFavorite())
                .isLiked(isLiked)
                .animeName(animeNames)
                .hashtags(hashtags)
                .build();
    }
}
