package com.otakumap.domain.place_review.repository;

import com.otakumap.domain.place_review.entity.PlaceReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PlaceReviewRepository extends JpaRepository<PlaceReview, Long>, PlaceReviewRepositoryCustom {
    Page<PlaceReview> findAllByUserId(Long userId, PageRequest pageRequest);
}
