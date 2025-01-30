package com.otakumap.domain.animation.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class AnimationRequestDTO {
    @Getter
    public static class AnimationCreationRequestDTO {
        @NotBlank
        String name;
    }
}
