package com.otakumap.domain.animation.service;

import com.otakumap.domain.animation.repository.AnimationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimationQueryServiceImpl implements AnimationQueryService {
    private final AnimationRepository animationRepository;

    @Override
    public boolean existsById(Long animationId) {
        return animationRepository.existsById(animationId);
    }
}
