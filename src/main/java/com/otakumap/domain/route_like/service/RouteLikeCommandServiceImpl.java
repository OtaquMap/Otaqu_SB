package com.otakumap.domain.route_like.service;

import com.otakumap.domain.route.entity.Route;
import com.otakumap.domain.route.repository.RouteRepository;
import com.otakumap.domain.route_like.entity.RouteLike;
import com.otakumap.domain.route_like.enums.Status;
import com.otakumap.domain.route_like.repository.RouteLikeRepository;
import com.otakumap.domain.user.entity.User;
import com.otakumap.domain.user.repository.UserRepository;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.RouteHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteLikeCommandServiceImpl implements RouteLikeCommandService {

    private final RouteLikeRepository routeLikeRepository;
    private final RouteRepository routeRepository;
    private final UserRepository userRepository;
    @Override
    public void saveRouteLike(User user, Long routeId) {

        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RouteHandler(ErrorStatus.ROUTE_NOT_FOUND));

        if(routeLikeRepository.existsByUserAndRoute(user, route)) {
            throw new RouteHandler(ErrorStatus.ROUTE_LIKE_ALREADY_EXISTS);
        }

        RouteLike routeLike = RouteLike.builder()
                .user(user)
                .route(route)
                .status(Status.ACTIVE)
                .isFavorite(Boolean.TRUE)
                .build();

        routeLikeRepository.save(routeLike);
    }
}
