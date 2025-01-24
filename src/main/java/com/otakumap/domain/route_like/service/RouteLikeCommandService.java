package com.otakumap.domain.route_like.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RouteLikeCommandService {
    void deleteRouteLike(List<Long> routeIds);
}