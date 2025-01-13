package com.otakumap.domain.eventShortReview.service;

import com.otakumap.domain.eventShortReview.dto.EventShortReviewRequestDTO;
import com.otakumap.domain.eventShortReview.entity.EventShortReview;
import org.springframework.data.domain.Page;

public interface EventShortReviewCommandService {
    EventShortReview createEventShortReview(Long eventId, EventShortReviewRequestDTO.NewEventShortReviewDTO request);
    Page<EventShortReview> getEventShortReviewsByEventId(Long eventId, Integer page);
}
