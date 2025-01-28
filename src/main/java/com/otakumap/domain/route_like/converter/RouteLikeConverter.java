package com.otakumap.domain.route_like.converter;

import com.otakumap.domain.route.entity.Route;
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
}
