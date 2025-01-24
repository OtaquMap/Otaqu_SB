package com.otakumap.domain.route_like.service;

import com.otakumap.domain.route_like.repository.RouteLikeRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteLikeCommandServiceImpl implements RouteLikeCommandService {

    private final RouteLikeRepository routeLikeRepository;
    private final EntityManager entityManager;

    @Override
    public void deleteRouteLike(List<Long> routeIds) {
        routeLikeRepository.deleteAllByIdInBatch(routeIds);
        entityManager.flush();
        entityManager.clear();
    }

}
