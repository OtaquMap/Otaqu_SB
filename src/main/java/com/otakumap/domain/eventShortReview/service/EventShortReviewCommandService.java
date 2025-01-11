package com.otakumap.domain.eventShortReview.service;

import com.otakumap.domain.eventShortReview.dto.EventShortReviewRequestDTO;
import com.otakumap.domain.eventShortReview.entity.EventShortReview;

public interface EventShortReviewCommandService {
    EventShortReview createEventShortReview(Long eventId, EventShortReviewRequestDTO.NewEventShortReviewDTO request);
}
