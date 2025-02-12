package com.otakumap.domain.route.service;

import com.otakumap.domain.mapping.PlaceAnimation;
import com.otakumap.domain.place.DTO.PlaceResponseDTO;
import com.otakumap.domain.place.converter.PlaceConverter;
import com.otakumap.domain.place.entity.Place;
import com.otakumap.domain.place_animation.repository.PlaceAnimationRepository;
import com.otakumap.domain.route.dto.RouteResponseDTO;
import com.otakumap.domain.route.entity.Route;
import com.otakumap.domain.route.repository.RouteRepository;
import com.otakumap.domain.route_item.repository.RouteItemRepository;
import com.otakumap.domain.user.entity.User;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.RouteHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteQueryServiceImpl implements RouteQueryService {
    private final RouteRepository routeRepository;
    private final RouteItemRepository routeItemRepository;
    private final PlaceAnimationRepository placeAnimationRepository;

    @Override
    public boolean isRouteExist(Long routeId) {
        return routeRepository.existsById(routeId);
    }

    @Override
    public RouteResponseDTO.RouteDetailDTO getRouteDetail(User user, Long routeId) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RouteHandler(ErrorStatus.ROUTE_NOT_FOUND));

        // routeId에 해당하는 Place 목록 조회
        List<Place> places = routeItemRepository.findPlacesByRouteId(routeId);

        // 해당하는 Place에 대한 PlaceAnimation 조회
        List<PlaceAnimation> placeAnimations = placeAnimationRepository.findByPlaceIn(places);

        // Animation 이름 추출 (중복 제거)
        List<String> animationNames = placeAnimations.stream()
                .map(pa -> pa.getAnimation().getName())
                .distinct()
                .toList();


        // 관련된 애니메이션이 없으면 예외 발생
        if (animationNames.isEmpty()) {
            throw new RouteHandler(ErrorStatus.ROUTE_ANIMATION_NOT_FOUND);
        }

        // 첫 번째 애니메이션 선택
        String animationName = animationNames.get(0);

        // PlaceDTO 변환
        List<PlaceResponseDTO.PlaceDTO> placeDTOs = PlaceConverter.toPlaceDTOList(places);

        return new RouteResponseDTO.RouteDetailDTO(
                route.getId(),
                route.getName(),
                animationName,
                placeDTOs
        );
    }
}