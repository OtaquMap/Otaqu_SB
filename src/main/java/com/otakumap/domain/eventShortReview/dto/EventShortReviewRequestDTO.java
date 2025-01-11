package com.otakumap.domain.eventShortReview.dto;

import com.otakumap.domain.User.entity.User;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;

public class EventShortReviewRequestDTO {

    @Getter
    public static class NewEventShortReviewDTO {
        Long userId; // 로그인 구현 전까지 임의로 request body로 받음
        Float rating;
        String content;

    }
}
