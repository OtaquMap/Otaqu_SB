package com.otakumap.domain.route.service;

import com.otakumap.domain.route.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RouteQueryServiceImpl implements RouteQueryService {
    private final RouteRepository routeRepository;

    @Override
    public boolean isRouteExist(Long routeId) {
        return routeRepository.existsById(routeId);
    }
}