package com.otakumap.domain.place_short_review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class PlaceShortReviewRequestDTO {
    @Getter
    public static class CreateDTO {
        Long userId; // 토큰 사용 전 임시
        @NotNull
        Float rating;
        @NotBlank
        String content;
    }
}
