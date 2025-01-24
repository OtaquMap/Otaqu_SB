package com.otakumap.domain.place_review.converter;

import com.otakumap.domain.animation.entity.Animation;
import com.otakumap.domain.image.converter.ImageConverter;
import com.otakumap.domain.place.entity.Place;
import com.otakumap.domain.place_review.dto.PlaceReviewRequestDTO;
import com.otakumap.domain.place_review.dto.PlaceReviewResponseDTO;
import com.otakumap.domain.place_review.entity.PlaceReview;
import com.otakumap.domain.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;

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

    // PlaceReview -> PlaceReviewDTO 변환
    public static PlaceReviewResponseDTO.PlaceReviewDTO toPlaceReviewDTO(PlaceReview placeReview) {
        return PlaceReviewResponseDTO.PlaceReviewDTO.builder()
                .reviewId(placeReview.getId())
                .placeId(placeReview.getPlace().getId())
                .title(placeReview.getTitle())
                .content(placeReview.getContent())
                .view(placeReview.getView())
                .createdAt(placeReview.getCreatedAt())
                .reviewImage(ImageConverter.toImageDTO(placeReview.getImage()))
                .build();
    }

    // 해당 장소의 애니메이션별 리뷰 그룹 생성
    public static PlaceReviewResponseDTO.AnimationReviewGroupDTO toAnimationReviewGroupDTO(Animation animation, List<PlaceReview> reviews) {

        List<PlaceReviewResponseDTO.PlaceReviewDTO> reviewDTOs = reviews.stream()
                .map(PlaceReviewConverter::toPlaceReviewDTO)
                .toList();

        return PlaceReviewResponseDTO.AnimationReviewGroupDTO.builder()
                .animationId(animation.getId())
                .animationName(animation.getName())
                .reviews(reviewDTOs)
                .totalReviews(reviews.size())
                .build();
    }

    // 최상위 결과 DTO 생성
    public static PlaceReviewResponseDTO.PlaceAnimationReviewDTO toPlaceAnimationReviewDTO(Place place, long totalReviews, List<PlaceReviewResponseDTO.AnimationReviewGroupDTO> animationGroups) {

        return PlaceReviewResponseDTO.PlaceAnimationReviewDTO.builder()
                .placeId(place.getId())
                .placeName(place.getName())
                .animationGroups(animationGroups)
                .totalReviews(totalReviews)
                .build();
    }
}
