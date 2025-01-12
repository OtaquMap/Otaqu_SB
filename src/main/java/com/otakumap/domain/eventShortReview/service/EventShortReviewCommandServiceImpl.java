package com.otakumap.domain.eventShortReview.service;

import com.otakumap.domain.user.entity.User;
import com.otakumap.domain.user.repository.UserRepository;
import com.otakumap.domain.event.entity.Event;
import com.otakumap.domain.event.repository.EventRepository;
import com.otakumap.domain.eventShortReview.converter.EventShortReviewConverter;
import com.otakumap.domain.eventShortReview.dto.EventShortReviewRequestDTO;
import com.otakumap.domain.eventShortReview.entity.EventShortReview;
import com.otakumap.domain.eventShortReview.repository.EventShortReviewRepository;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.EventHandler;
import com.otakumap.global.apiPayload.exception.handler.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EventShortReviewCommandServiceImpl implements EventShortReviewCommandService{
    private final UserRepository userRepository;
    private final EventShortReviewRepository eventShortReviewRepository;
    private final EventRepository eventRepository;

    @Override
    @Transactional
    public EventShortReview createEventShortReview(Long eventId, EventShortReviewRequestDTO.NewEventShortReviewDTO request) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventHandler(ErrorStatus.EVENT_NOT_FOUND));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        EventShortReview eventShortReview = EventShortReviewConverter.toEventShortReview(request, event, user);

        return eventShortReviewRepository.save(eventShortReview);
    }

    @Override
    public Page<EventShortReview> getEventShortReviewsByEventId(Long eventId, Integer page) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventHandler(ErrorStatus.EVENT_NOT_FOUND));

        return eventShortReviewRepository.findAllByEvent(event, PageRequest.of(page, 4));
    }
}
