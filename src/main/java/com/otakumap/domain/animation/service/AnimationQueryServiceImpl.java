package com.otakumap.domain.animation.service;

import com.otakumap.domain.animation.entity.Animation;

import java.util.List;

public class AnimationQueryServiceImpl implements AnimationQueryService {
    private final AnimationRepository animationRepository;

    @Override
    public List<Animation> searchAnimation(String keyword) {
        return animationRepository.searchAnimationByKeyword(keyword);
    }
}
