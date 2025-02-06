package com.otakumap.domain.route_like.converter;

import com.otakumap.domain.route.entity.Route;
import com.otakumap.domain.route_like.dto.RouteLikeResponseDTO;
import com.otakumap.domain.route_like.entity.RouteLike;
import com.otakumap.domain.user.entity.User;

public class RouteLikeConverter {
    public static RouteLike toRouteLike(User user, Route route) {
        return RouteLike.builder()
                .name(route.getName())
                .user(user)
                .route(route)
                .isFavorite(Boolean.FALSE)
                .build();
    }

    public static RouteLikeResponseDTO.FavoriteResultDTO toFavoriteResultDTO(RouteLike routeLike) {
        return RouteLikeResponseDTO.FavoriteResultDTO.builder()
                .routeLikeId(routeLike.getId())
                .isFavorite(routeLike.getIsFavorite())
                .build();
    }

    public static RouteLikeResponseDTO.CustomRouteSaveResultDTO toCustomRouteSaveResultDTO(RouteLike routeLike) {
        return RouteLikeResponseDTO.CustomRouteSaveResultDTO.builder()
                .routeId(routeLike.getRoute().getId())
                .createdAt(routeLike.getCreatedAt())
                .build();
    }

    public static RouteLikeResponseDTO.RouteUpdateResultDTO toRouteUpdateResultDTO(RouteLike routeLike) {
        return RouteLikeResponseDTO.RouteUpdateResultDTO.builder()
                .routeId(routeLike.getRoute().getId())
                .updatedAt(routeLike.getUpdatedAt())
                .build();
    }
}
