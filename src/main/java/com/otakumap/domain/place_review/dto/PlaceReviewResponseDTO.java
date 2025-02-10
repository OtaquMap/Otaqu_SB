package com.otakumap.domain.place_review.dto;

import com.otakumap.domain.hash_tag.dto.HashTagResponseDTO;
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
        private List<Long> placeIds;
        private String title;
        private String content;
        private Long view;
        private LocalDateTime createdAt;
        private ImageResponseDTO.ImageDTO reviewImage;
        String type;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AnimationReviewGroupDTO {
        private Long animationId;
        private String animationName;
        private List<PlaceReviewDTO> reviews;
        private long totalReviews;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PlaceAnimationReviewDTO {
        private Long placeId;
        private String placeName;
        private Float avgRating;
        private long totalReviews;
        // place_hashtag -> place_animation_hashtag 변경에 따라 일단 주석 처리
        //private List<HashTagResponseDTO.HashTagDTO> hashTags;
        private List<AnimationReviewGroupDTO> animationGroups;
    }

}
