package com.otakumap.domain.place_review.converter;

import com.otakumap.domain.place.entity.Place;
import com.otakumap.domain.place_review.dto.PlaceReviewRequestDTO;
import com.otakumap.domain.place_review.dto.PlaceReviewResponseDTO;
import com.otakumap.domain.place_review.entity.PlaceReview;
import com.otakumap.domain.user.entity.User;

import java.time.LocalDateTime;

public class PlaceReviewConverter {
    public static PlaceReviewResponseDTO.ReviewCreateResponseDTO toReviewCreateResponseDTO(PlaceReview placeReview) {
        return PlaceReviewResponseDTO.ReviewCreateResponseDTO.builder()
                .reviewId(placeReview.getId())
                .title(placeReview.getTitle())
                .content(placeReview.getContent())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static PlaceReview toPlaceReview(PlaceReviewRequestDTO.ReviewCreateRequestDTO request, User user, Place place) {
        return PlaceReview.builder()
                .user(user)
                .place(place)
                .title(request.getTitle())
                .content(request.getContent())
                .view(0L)
                .build();
    }
}
