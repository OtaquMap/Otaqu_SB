package com.otakumap.domain.reviews.dto;

import com.otakumap.domain.image.dto.ImageResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReviewResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchedReviewPreViewDTO {
        Long reviewId; // 검색된 Review의 id
        Long id; // Event 또는 Place의 id
        String title;
        String content;
        String type; // "event" 또는 "place"
        Long view;
        LocalDateTime createdAt;
        ImageResponseDTO.ImageDTO reviewImage;
    }
}