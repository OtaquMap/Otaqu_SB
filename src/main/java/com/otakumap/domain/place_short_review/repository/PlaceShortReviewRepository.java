package com.otakumap.domain.place_short_review.repository;

import com.otakumap.domain.place.entity.Place;
import com.otakumap.domain.place_short_review.entity.PlaceShortReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceShortReviewRepository extends JpaRepository<PlaceShortReview, Long> {
    Page<PlaceShortReview> findAllByPlace(Place place, PageRequest pageRequest);
}
