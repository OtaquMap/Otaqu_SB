package com.otakumap.domain.event_location.repository;

import com.otakumap.domain.event.entity.Event;
import com.otakumap.domain.event_location.entity.EventLocation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EventLocationRepository extends JpaRepository<EventLocation, Long> {
    boolean existsByLatitudeAndLongitude(String lat, String lng);
    Optional<EventLocation> findByLatitudeAndLongitude(String lat, String lng);
}
