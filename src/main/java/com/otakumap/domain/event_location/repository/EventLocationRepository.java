package com.otakumap.domain.event_location.repository;

import com.otakumap.domain.event_location.entity.EventLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventLocationRepository extends JpaRepository<EventLocation, Long> {
    boolean existsByLatAndLng(Double latitude, Double longitude);
    List<EventLocation> findByLatAndLng(Double latitude, Double longitude);
}