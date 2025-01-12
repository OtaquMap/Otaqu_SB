package com.otakumap.domain.place_review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class PlaceReviewRequestDTO {
    @Getter
    public static class ReviewCreateRequestDTO {
        private Long userId; // 토큰 사용 전 임시로 사용
        private Long placeId; // 임시
        @NotBlank
        @Size(max = 20)
        private String title;
        @NotBlank
        private String content;
        // TODO: 이미지, 장소 활용
    }
}
