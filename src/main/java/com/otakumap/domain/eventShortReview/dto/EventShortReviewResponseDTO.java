package com.otakumap.domain.eventShortReview.dto;

import com.otakumap.domain.image.dto.ImageResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class EventShortReviewResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NewEventShortReviewDTO {
        Long userId;
        Long eventId;
        String content;
        Float rating;
        String userName;
        ImageResponseDTO.ImageDTO profileImage;
        int likes;
        int dislikes;
    }
}
