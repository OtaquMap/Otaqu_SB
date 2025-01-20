package com.otakumap.domain.route.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RouteCommandService {
    void deleteRouteLike(List<Long> routeIds);
}