package com.otakumap.domain.place_short_review.converter;

import com.otakumap.domain.place.entity.Place;
import com.otakumap.domain.place_short_review.dto.PlaceShortReviewRequestDTO;
import com.otakumap.domain.place_short_review.dto.PlaceShortReviewResponseDTO;
import com.otakumap.domain.place_short_review.entity.PlaceShortReview;
import com.otakumap.domain.user.entity.User;

public class PlaceShortReviewConverter {
    public static PlaceShortReviewResponseDTO.CreateReviewDTO toCreateReviewDTO(PlaceShortReview placeShortReview) {
        User user = placeShortReview.getUser();
        return PlaceShortReviewResponseDTO.CreateReviewDTO.builder()
                .reviewId(placeShortReview.getId())
                .rating(placeShortReview.getRating().intValue())
                .content(placeShortReview.getContent())
                .createdAt(placeShortReview.getCreatedAt())
                .userId(user.getId())
                .nickname(user.getNickname())
                .build();
    }

    public static PlaceShortReview toPlaceShortReview(PlaceShortReviewRequestDTO.CreateDTO request, User user) {
        return PlaceShortReview.builder()
                .user(user)
                .rating(request.getRating())
                .content(request.getContent())
                .build();
    }
}
