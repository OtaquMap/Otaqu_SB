package com.otakumap.domain.place_short_review.service;

import com.otakumap.domain.place_short_review.DTO.PlaceShortReviewRequestDTO;
import com.otakumap.domain.place_short_review.entity.PlaceShortReview;
import com.otakumap.domain.user.entity.User;

public interface PlaceShortReviewCommandService {
    PlaceShortReview createReview(User user, Long placeId, PlaceShortReviewRequestDTO.CreateDTO request);
}
