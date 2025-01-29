package com.otakumap.domain.event_short_review.dto;

import lombok.Getter;

public class EventShortReviewRequestDTO {

    @Getter
    public static class NewEventShortReviewDTO {
        Long userId; // 로그인 구현 전까지 임의로 request body로 받음
        Float rating;
        String content;

    }
}
