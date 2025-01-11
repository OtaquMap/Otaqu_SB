package com.otakumap.domain.eventShortReview.repository;

import com.otakumap.domain.eventShortReview.entity.EventShortReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventShortReviewRepository extends JpaRepository<EventShortReview, Long> {
}
