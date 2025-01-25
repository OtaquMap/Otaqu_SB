package com.otakumap.domain.event_like.service;

import com.otakumap.domain.event.entity.enums.EventType;
import com.otakumap.domain.event_like.converter.EventLikeConverter;
import com.otakumap.domain.event_like.dto.EventLikeResponseDTO;
import com.otakumap.domain.event_like.entity.EventLike;
import com.otakumap.domain.event_like.repository.EventLikeRepository;
import com.otakumap.domain.user.entity.User;
import com.otakumap.domain.user.repository.UserRepository;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.EventHandler;
import com.otakumap.global.apiPayload.exception.handler.UserHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class EventLikeQueryServiceImpl implements EventLikeQueryService {
    private final EventLikeRepository eventLikeRepository;
    private final UserRepository userRepository;

    @Override
    public EventLikeResponseDTO.EventLikePreViewListDTO getEventLikeList(User user, Integer type, Long lastId, int limit) {
        List<EventLike> result;
        Pageable pageable = PageRequest.of(0, limit + 1);
        EventType eventType = (type == null || type == 0) ? null : EventType.values()[type - 1];

        if (lastId.equals(0L)) {
            result = (eventType == null)
                    ? eventLikeRepository.findAllByUserIsOrderByCreatedAtDesc(user, pageable).getContent()
                    : eventLikeRepository.findAllByUserIsAndEventTypeOrderByCreatedAtDesc(user, eventType, pageable).getContent();
        } else {
            EventLike eventLike = eventLikeRepository.findById(lastId).orElseThrow(() -> new EventHandler(ErrorStatus.EVENT_LIKE_NOT_FOUND));
            result =  (eventType == null)
                    ? eventLikeRepository.findAllByUserIsAndCreatedAtLessThanOrderByCreatedAtDesc(user, eventLike.getCreatedAt(), pageable).getContent()
                    : eventLikeRepository.findAllByUserIsAndEventTypeAndCreatedAtLessThanOrderByCreatedAtDesc(user, eventType, eventLike.getCreatedAt(), pageable).getContent();
        }
        return createEventLikePreviewListDTO(result, limit);
    }


    private EventLikeResponseDTO.EventLikePreViewListDTO createEventLikePreviewListDTO(List<EventLike> eventLikes, int limit) {
        boolean hasNext = eventLikes.size() > limit;
        Long lastId = null;
        if (hasNext) {
            eventLikes = eventLikes.subList(0, eventLikes.size() - 1);
            lastId = eventLikes.get(eventLikes.size() - 1).getId();
        }
        List<EventLikeResponseDTO.EventLikePreViewDTO> list = eventLikes
                .stream()
                .map(EventLikeConverter::eventLikePreViewDTO)
                .collect(Collectors.toList());

        return EventLikeConverter.eventLikePreViewListDTO(list, hasNext, lastId);
    }

    @Override
    public boolean isEventLikeExist(Long id) {
        return eventLikeRepository.existsById(id);
    }
}
