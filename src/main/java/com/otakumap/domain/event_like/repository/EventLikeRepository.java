package com.otakumap.domain.event_like.repository;

import com.otakumap.domain.event_like.entity.EventLike;;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventLikeRepository extends JpaRepository<EventLike, Long> {
}
