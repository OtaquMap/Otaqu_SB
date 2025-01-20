package com.otakumap.domain.place_review.repository;

import com.otakumap.domain.place_review.entity.PlaceReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceReviewRepository extends JpaRepository<PlaceReview, Long>, PlaceReviewRepositoryCustom {
}
