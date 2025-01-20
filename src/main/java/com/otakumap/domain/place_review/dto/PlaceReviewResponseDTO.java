package com.otakumap.domain.place_review.dto;

import com.otakumap.domain.image.dto.ImageResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlaceReviewDTO {
        private Long reviewId;
        private Long placeId; // 나중에 삭제
        String title;
        String content;
        Long view;
        LocalDateTime createdAt;
        ImageResponseDTO.ImageDTO reviewImage;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnimationReviewGroupDTO {
        private Long animationId;
        private String animationName;
        private List<PlaceReviewDTO> reviews;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlaceAnimationReviewDTO {
        private Long placeId;
        private String placeName;
        private List<AnimationReviewGroupDTO> animationGroups;
    }

}
