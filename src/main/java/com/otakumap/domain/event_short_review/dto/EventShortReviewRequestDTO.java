package com.otakumap.domain.event_short_review.dto;

import lombok.Getter;

public class EventShortReviewRequestDTO {

    @Getter
    public static class NewEventShortReviewDTO {
        Float rating;
        String content;
    }
}
