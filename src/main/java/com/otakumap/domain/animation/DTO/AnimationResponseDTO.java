package com.otakumap.domain.animation.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class AnimationResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnimationResultDTO {
        Long animationId;
        String name;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnimationResultListDTO {
        List<AnimationResultDTO> animations;
        Integer listSize;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnimationCreationResponseDTO {
        Long animationId;
        String name;
        LocalDateTime createdAt;
    }
}
