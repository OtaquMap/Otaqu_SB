package com.otakumap.domain.eventShortReview.repository;

import com.otakumap.domain.event.entity.Event;
import com.otakumap.domain.eventShortReview.entity.EventShortReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventShortReviewRepository extends JpaRepository<EventShortReview, Long> {
    Page<EventShortReview> findAllByEvent(Event event, PageRequest pageRequest);
}
