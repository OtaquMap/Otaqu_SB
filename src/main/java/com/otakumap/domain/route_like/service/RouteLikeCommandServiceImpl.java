package com.otakumap.domain.route_like.service;

import com.otakumap.domain.place.entity.Place;
import com.otakumap.domain.place.repository.PlaceRepository;
import com.otakumap.domain.route.converter.RouteConverter;
import com.otakumap.domain.route.entity.Route;
import com.otakumap.domain.route.repository.RouteRepository;
import com.otakumap.domain.route_item.converter.RouteItemConverter;
import com.otakumap.domain.route_item.entity.RouteItem;
import com.otakumap.domain.route_like.converter.RouteLikeConverter;
import com.otakumap.domain.route_like.dto.RouteLikeRequestDTO;
import com.otakumap.domain.route_like.entity.RouteLike;
import com.otakumap.domain.route_like.repository.RouteLikeRepository;
import com.otakumap.domain.user.entity.User;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.PlaceHandler;
import com.otakumap.global.apiPayload.exception.handler.RouteHandler;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteLikeCommandServiceImpl implements RouteLikeCommandService {
    private final RouteLikeRepository routeLikeRepository;
    private final RouteRepository routeRepository;
    private final EntityManager entityManager;
    private final PlaceRepository placeRepository;

    @Override
    public void saveRouteLike(User user, Long routeId) {
        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RouteHandler(ErrorStatus.ROUTE_NOT_FOUND));

        if (routeLikeRepository.existsByUserAndRoute(user, route)) {
            throw new RouteHandler(ErrorStatus.ROUTE_LIKE_ALREADY_EXISTS);
        }

        RouteLike routeLike = RouteLikeConverter.toRouteLike(user, route);
        routeLikeRepository.save(routeLike);
    }

    @Transactional
    @Override
    public void deleteRouteLike(List<Long> routeIds) {
        routeLikeRepository.deleteAllByIdInBatch(routeIds);
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public void updateName(Long routeId, String name) {

        // 루트가 저장되어있는지 확인
        RouteLike routeLike = routeLikeRepository.findByRouteId(routeId)
                .orElseThrow(() -> new RouteHandler(ErrorStatus.ROUTE_NOT_FOUND));

        // 이름 변경
        routeLike.setName(name);

        // 변경된 엔티티 저장
        routeLikeRepository.save(routeLike);
    }

    @Override
    public RouteLike favoriteRouteLike(Long routeLikeId, RouteLikeRequestDTO.FavoriteDTO request) {
        RouteLike routeLike = routeLikeRepository.findById(routeLikeId).orElseThrow(() -> new RouteHandler(ErrorStatus.ROUTE_LIKE_NOT_FOUND));
        routeLike.setIsFavorite(request.getIsFavorite());
        return routeLikeRepository.save(routeLike);
    }

    @Override
    public void saveCustomRouteLike(RouteLikeRequestDTO.SaveCustomRouteLikeDTO request, User user) {
        List<RouteItem> routeItems = request.getRouteItems()
                .stream()
                .map(routeItem -> {
                    Place place = placeRepository.findById(routeItem.getPlaceId()).orElseThrow(() -> new PlaceHandler(ErrorStatus.PLACE_NOT_FOUND));
                    return RouteItemConverter.toRouteItem(routeItem.getItemOrder(), place);
                }).toList();

        Route route = RouteConverter.toRoute(request.getName(), routeItems);

        routeRepository.save(route);
        routeLikeRepository.save(RouteLikeConverter.toRouteLike(user, route));
    }
}
