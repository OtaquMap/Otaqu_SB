package com.otakumap.domain.eventShortReview.dto;

import com.otakumap.domain.image.dto.ImageResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventShortReviewListDTO {
        List<EventShortReviewDTO> eventShortReviewList;
        Integer currentPage;
        Integer totalPages;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class EventShortReviewDTO {
        Long id;
        String content;
        Float rating;
        ImageResponseDTO.ImageDTO profileImage;
    }
}
