package com.otakumap.domain.event_like.repository;

import com.otakumap.domain.event.entity.enums.EventType;
import com.otakumap.domain.event_like.entity.EventLike;
import com.otakumap.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface EventLikeRepository extends JpaRepository<EventLike, Long> {
    Page<EventLike> findAllByUserIsOrderByCreatedAtDesc(User user, Pageable pageable);
    Page<EventLike> findAllByUserIsAndCreatedAtLessThanOrderByCreatedAtDesc(User user, LocalDateTime createdAt, Pageable pageable);
    Page<EventLike> findAllByUserIsAndEventTypeOrderByCreatedAtDesc(User user, EventType type, Pageable pageable);
    Page<EventLike> findAllByUserIsAndEventTypeAndCreatedAtLessThanOrderByCreatedAtDesc(User user,  EventType type, LocalDateTime createdAt, Pageable pageable);
}
