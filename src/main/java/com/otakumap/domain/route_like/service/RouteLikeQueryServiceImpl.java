package com.otakumap.domain.route_like.service;

import com.otakumap.domain.route_like.repository.RouteLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RouteLikeQueryServiceImpl implements RouteLikeQueryService {
    private final RouteLikeRepository routeLikeRepository;

    @Override
    public boolean isRouteLikeExist(Long id) {
        return routeLikeRepository.existsById(id);
    }
}
