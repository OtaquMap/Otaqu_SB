package com.otakumap.domain.place.repository;

import com.otakumap.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByNameAndLatAndLng(String name, Double lat, Double lng);
}
