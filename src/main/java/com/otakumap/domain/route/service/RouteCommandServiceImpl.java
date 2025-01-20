package com.otakumap.domain.route.service;

import com.otakumap.domain.route.repository.RouteRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteCommandServiceImpl implements RouteCommandService {

    private final RouteRepository routeRepository;
    private final EntityManager entityManager;

    @Override
    public void deleteRouteLike(List<Long> routeIds) {
        routeRepository.deleteAllByIdInBatch(routeIds);
        entityManager.flush();
        entityManager.clear();
    }

}
