package com.otakumap.domain.place_review_place.repository;

import com.otakumap.domain.mapping.PlaceReviewPlace;
import com.otakumap.domain.place_review.entity.PlaceReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceReviewPlaceRepository extends JpaRepository<PlaceReviewPlace, Long> {
    PlaceReviewPlace findByPlaceReview(PlaceReview placeReview);
}
