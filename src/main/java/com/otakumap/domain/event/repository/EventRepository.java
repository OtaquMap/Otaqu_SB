package com.otakumap.domain.event.repository;

import com.otakumap.domain.event.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByStartDate(LocalDate startDate);
}
