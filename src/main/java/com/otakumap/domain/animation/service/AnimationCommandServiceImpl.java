package com.otakumap.domain.animation.service;

import com.otakumap.domain.animation.converter.AnimationConverter;
import com.otakumap.domain.animation.entity.Animation;
import com.otakumap.domain.animation.repository.AnimationRepository;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.AnimationHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimationCommandServiceImpl implements AnimationCommandService {
    private final AnimationRepository animationRepository;

    @Override
    @Transactional
    public Animation createAnimation(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new AnimationHandler(ErrorStatus.ANIMATION_NAME_IS_EMPTY);
        }

        if (name.length() < 2 || name.length() > 50) {
            throw new AnimationHandler(ErrorStatus.ANIMATION_NAME_LENGTH);
        }

        if (!name.matches("^[가-힣a-zA-Z0-9\\\\s./()-]+$")) {
            throw new AnimationHandler(ErrorStatus.ANIMATION_NAME_SPECIAL_CHARACTER);
        }

        String normalizedName = name.replaceAll("\\s+", "").toLowerCase();

        // 중복 검사
        if (animationRepository.findAll().stream()
                .anyMatch(animation -> animation.getName().replaceAll("\\s+", "").toLowerCase().equals(normalizedName))) {
            throw new AnimationHandler(ErrorStatus.ANIMATION_ALREADY_EXISTS);
        }

        Animation newAnimation = AnimationConverter.toAnimation(name);
        return animationRepository.save(newAnimation);
    }
}
