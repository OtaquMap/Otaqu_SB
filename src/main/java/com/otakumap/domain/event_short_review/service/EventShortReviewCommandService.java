package com.otakumap.domain.event_short_review.service;

import com.otakumap.domain.event_short_review.dto.EventShortReviewRequestDTO;
import com.otakumap.domain.event_short_review.entity.EventShortReview;
import org.springframework.data.domain.Page;

public interface EventShortReviewCommandService {
    EventShortReview createEventShortReview(Long eventId, EventShortReviewRequestDTO.NewEventShortReviewDTO request);
    Page<EventShortReview> getEventShortReviewsByEventId(Long eventId, Integer page);
}
