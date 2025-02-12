package com.otakumap.domain.place.repository;

import com.otakumap.domain.place.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {
 List<Place> findByLatAndLng(Double lat, Double lng);
    Optional<Place> findByNameAndLatAndLng(String name, Double lat, Double lng);
}