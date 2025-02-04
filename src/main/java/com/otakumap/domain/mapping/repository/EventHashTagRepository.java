package com.otakumap.domain.mapping.repository;

import com.otakumap.domain.event.entity.Event;
import com.otakumap.domain.mapping.EventHashTag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventHashTagRepository extends JpaRepository<EventHashTag, Long> {
    List<EventHashTag> findByEvent(Event event);
}
