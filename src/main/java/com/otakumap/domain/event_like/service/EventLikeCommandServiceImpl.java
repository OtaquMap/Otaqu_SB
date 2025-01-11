package com.otakumap.domain.event_like.service;

import com.otakumap.domain.event_like.repository.EventLikeRepository;
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
    private final EntityManager entityManager;

    @Override
    public void deleteEventLike(List<Long> eventIds) {
        eventLikeRepository.deleteAllByIdInBatch(eventIds);
        entityManager.flush();
        entityManager.clear();
    }
}
