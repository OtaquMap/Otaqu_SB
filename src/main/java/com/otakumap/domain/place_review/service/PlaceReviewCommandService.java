package com.otakumap.domain.place_review.service;

import com.otakumap.domain.place_review.entity.PlaceReview;
import com.otakumap.domain.user.entity.User;

public interface PlaceReviewCommandService {
    void deleteAllByUserId(Long userId);
}
