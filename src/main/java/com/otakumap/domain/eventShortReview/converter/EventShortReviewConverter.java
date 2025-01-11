package com.otakumap.domain.eventShortReview.converter;

import com.otakumap.domain.User.entity.User;
import com.otakumap.domain.event.entity.Event;
import com.otakumap.domain.eventShortReview.dto.EventShortReviewRequestDTO;
import com.otakumap.domain.eventShortReview.dto.EventShortReviewResponseDTO;
import com.otakumap.domain.eventShortReview.entity.EventShortReview;
import com.otakumap.domain.image.converter.ImageConverter;

import java.time.LocalDateTime;

public class EventShortReviewConverter {
    public static EventShortReview toEventShortReview(EventShortReviewRequestDTO.NewEventShortReviewDTO request, Event event, User user) {
        return EventShortReview.builder()
                .event(event)
                .user(user)
                .rating(request.getRating())
                .content(request.getContent())
                .build();
    }

    public static EventShortReviewResponseDTO.NewEventShortReviewDTO toNewEventShortReviewDTO(EventShortReview eventShortReview) {
        return EventShortReviewResponseDTO.NewEventShortReviewDTO.builder()
                .userId(eventShortReview.getUser().getId())
                .userName(eventShortReview.getUser().getName())
                .eventId(eventShortReview.getEvent().getId())
                .content(eventShortReview.getContent())
                .rating(eventShortReview.getRating())
                .profileImage(ImageConverter.toImageDTO(eventShortReview.getUser().getProfileImage()))
                .build();
    }
}
