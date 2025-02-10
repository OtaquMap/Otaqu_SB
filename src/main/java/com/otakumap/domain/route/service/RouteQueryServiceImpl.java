package com.otakumap.domain.route.service;

import com.otakumap.domain.place.DTO.PlaceResponseDTO;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteQueryServiceImpl implements RouteQueryService {
    private final RouteRepository routeRepository;
    private final RouteItemRepository routeItemRepository;

    @Override
    public boolean isRouteExist(Long routeId) {
        return routeRepository.existsById(routeId);
    }

    @Override
    public RouteResponseDTO.RouteDetailDTO getRouteDetail(User user, Long routeId) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RouteHandler(ErrorStatus.ROUTE_NOT_FOUND));

        // routeId로 Place 목록 조회
        List<PlaceResponseDTO.PlaceDTO> places = routeItemRepository.findPlacesByRouteId(routeId)
                .stream()
                .map(place -> new PlaceResponseDTO.PlaceDTO(
                        place.getId(),
                        place.getName(),
                        place.getLat(),
                        place.getLng()
                ))
                .collect(Collectors.toList());

        return new RouteResponseDTO.RouteDetailDTO(route.getId(), places);
    }
}