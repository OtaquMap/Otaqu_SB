package com.otakumap.domain.reviews.converter;

import com.otakumap.domain.animation.entity.Animation;
import com.otakumap.domain.event_review.entity.EventReview;
import com.otakumap.domain.image.converter.ImageConverter;
import com.otakumap.domain.place_review.entity.PlaceReview;
import com.otakumap.domain.reviews.dto.ReviewResponseDTO;

import java.util.List;

public class ReviewConverter {

    public static ReviewResponseDTO.Top7ReviewPreViewDTO toTop7ReviewPreViewDTO(EventReview eventReview) {
        return ReviewResponseDTO.Top7ReviewPreViewDTO.builder()
                .id(eventReview.getId())
                .title(eventReview.getTitle())
                .reviewImage(ImageConverter.toImageDTO(eventReview.getImage()))
                .view(eventReview.getView())
                .build();
    }

    public static ReviewResponseDTO.SearchedReviewPreViewDTO toSearchedEventReviewPreviewDTO(EventReview eventReview) {
        return ReviewResponseDTO.SearchedReviewPreViewDTO.builder()
                .reviewId(eventReview.getId())
                .id(eventReview.getEvent().getId())
                .title(eventReview.getTitle())
                .content(eventReview.getContent())
                .reviewImage(ImageConverter.toImageDTO(eventReview.getImage()))
                .view(eventReview.getView())
                .createdAt(eventReview.getCreatedAt())
                .type("event")
                .build();
    }

    public static ReviewResponseDTO.SearchedReviewPreViewDTO toSearchedPlaceReviewPreviewDTO(PlaceReview placeReview) {
        return ReviewResponseDTO.SearchedReviewPreViewDTO.builder()
                .reviewId(placeReview.getId())
                .id(placeReview.getPlace().getId())
                .title(placeReview.getTitle())
                .content(placeReview.getContent())
                .reviewImage(ImageConverter.toImageDTO(placeReview.getImage()))
                .view(placeReview.getView())
                .createdAt(placeReview.getCreatedAt())
                .type("place")
                .build();
    }

    public static ReviewResponseDTO.AnimationResultDTO animationResultDTO(Animation animation) {
        return ReviewResponseDTO.AnimationResultDTO.builder()
                .animationId(animation.getId())
                .name(animation.getName())
                .build();
    }

    public static ReviewResponseDTO.AnimationResultListDTO animationResultListDTO(List<Animation> animations) {
        List<ReviewResponseDTO.AnimationResultDTO> animationResultDTOs = animations.stream()
                .map(ReviewConverter::animationResultDTO)
                .toList();

        return ReviewResponseDTO.AnimationResultListDTO.builder()
                .animations(animationResultDTOs)
                .listSize(animationResultDTOs.size())
                .build();
    }
}
