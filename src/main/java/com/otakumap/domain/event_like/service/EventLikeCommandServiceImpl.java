package com.otakumap.domain.event_like.service;

import com.otakumap.domain.event.repository.EventRepository;
import com.otakumap.domain.event_like.entity.EventLike;
import com.otakumap.domain.event_like.repository.EventLikeRepository;
import com.otakumap.domain.user.entity.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class EventLikeCommandServiceImpl implements EventLikeCommandService {
    private final EventLikeRepository eventLikeRepository;
    private final EventRepository eventRepository;
    private final EntityManager entityManager;

    @Override
    public void addEventLike(User user, Long eventId) {
        eventLikeRepository.save(
                EventLike.builder()
                        .event(eventRepository.getReferenceById(eventId))
                        .user(user)
                        .isFavorite(true)
                        .build()
        );
        entityManager.flush();
        entityManager.clear();
    }

    @Override
    public void deleteEventLike(List<Long> eventIds) {
        eventLikeRepository.deleteAllByIdInBatch(eventIds);
        entityManager.flush();
        entityManager.clear();
    }
}
