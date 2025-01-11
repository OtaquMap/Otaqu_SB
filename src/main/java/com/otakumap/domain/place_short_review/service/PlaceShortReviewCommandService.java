package com.otakumap.domain.place_short_review.service;

import com.otakumap.domain.place_short_review.dto.PlaceShortReviewRequestDTO;
import com.otakumap.domain.place_short_review.entity.PlaceShortReview;

public interface PlaceShortReviewCommandService {
    PlaceShortReview createReview(Long placeId, PlaceShortReviewRequestDTO.CreateDTO request);
}
