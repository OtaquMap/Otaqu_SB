package com.otakumap.domain.route_like.service;

import com.otakumap.domain.user.entity.User;

public interface RouteLikeCommandService {
    void saveRouteLike(User user, Long routeId);
}
