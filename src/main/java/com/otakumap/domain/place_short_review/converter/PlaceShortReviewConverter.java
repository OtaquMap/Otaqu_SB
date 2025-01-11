package com.otakumap.domain.place_short_review.converter;

import com.otakumap.domain.place_short_review.DTO.PlaceShortReviewResponseDTO;
import com.otakumap.domain.place_short_review.entity.PlaceShortReview;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class PlaceShortReviewConverter {
    public static PlaceShortReviewResponseDTO.PlaceShortReviewDTO placeShortReviewDTO(PlaceShortReview placeShortReview) {
        PlaceShortReviewResponseDTO.PlaceShortReviewUserDTO user = PlaceShortReviewResponseDTO.PlaceShortReviewUserDTO.builder()
                .id(placeShortReview.getUser().getId())
                .nickname(placeShortReview.getUser().getNickname())
                //.profilePicture(placeShortReview.getUser().getProfilePicture()
                .build();

        return PlaceShortReviewResponseDTO.PlaceShortReviewDTO.builder()
                .id(placeShortReview.getId())
                .user(user)
                .content(placeShortReview.getContent())
                .rating(placeShortReview.getRating())
                .createdAt(placeShortReview.getCreatedAt())
                .likes(placeShortReview.getLikes())
                .dislikes(placeShortReview.getDislikes())
                .build();
    }

    public static PlaceShortReviewResponseDTO.PlaceShortReviewListDTO placeShortReviewListDTO(Page<PlaceShortReview> reviewList) {

        if(reviewList == null || reviewList.isEmpty()) return null;

        List<PlaceShortReviewResponseDTO.PlaceShortReviewDTO> placeShortReviewDTOList = reviewList.stream()
                .map(PlaceShortReviewConverter::placeShortReviewDTO).collect(Collectors.toList());

        PlaceShortReview review = reviewList.getContent().get(0);

        return PlaceShortReviewResponseDTO.PlaceShortReviewListDTO.builder()
                .placeId(review.getPlace().getId())
                .placeName(review.getPlace().getName())
                .currentPage(reviewList.getNumber() + 1)
                .totalPages(reviewList.getTotalPages())
                .shortReviews(placeShortReviewDTOList)
                .build();
    }
}
