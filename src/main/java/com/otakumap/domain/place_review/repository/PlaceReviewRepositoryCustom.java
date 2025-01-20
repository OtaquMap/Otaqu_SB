package com.otakumap.domain.place_review.repository;

import com.otakumap.domain.animation.entity.Animation;
import com.otakumap.domain.place_review.entity.PlaceReview;

import java.util.List;
import java.util.Map;

public interface PlaceReviewRepositoryCustom {

    Map<Animation, List<PlaceReview>> findReviewsByPlaceWithAnimation(Long placeId, int page, int size, String sort);
}
