package com.otakumap.domain.place_review.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class PlaceReviewResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ReviewCreateResponseDTO {
        private Long reviewId;
        private String title;
        private String content;
        LocalDateTime createdAt;
    }
}
