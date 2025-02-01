package com.otakumap.domain.reviews.converter;

import com.otakumap.domain.event.entity.Event;
import com.otakumap.domain.event_review.entity.EventReview;
import com.otakumap.domain.image.converter.ImageConverter;
import com.otakumap.domain.image.entity.Image;
import com.otakumap.domain.place.entity.Place;
import com.otakumap.domain.place_review.entity.PlaceReview;
import com.otakumap.domain.reviews.dto.ReviewRequestDTO;
import com.otakumap.domain.reviews.dto.ReviewResponseDTO;
import com.otakumap.domain.user.entity.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {

    public static ReviewResponseDTO.Top7ReviewPreViewDTO toTop7EventReviewPreViewDTO(EventReview eventReview) {
        return ReviewResponseDTO.Top7ReviewPreViewDTO.builder()
                .id(eventReview.getId())
                .title(eventReview.getTitle())
                .reviewImage(eventReview.getImages().isEmpty() ? null :
                        ImageConverter.toImageDTO(eventReview.getImages().get(0)))
                .view(eventReview.getView())
                .createdAt(eventReview.getCreatedAt())
                .type("event")
                .build();
    }

    public static ReviewResponseDTO.Top7ReviewPreViewDTO toTop7PlaceReviewPreViewDTO(PlaceReview placeReview) {
        return ReviewResponseDTO.Top7ReviewPreViewDTO.builder()
                .id(placeReview.getId())
                .title(placeReview.getTitle())
                .reviewImage(placeReview.getImages().isEmpty() ? null :
                        ImageConverter.toImageDTO(placeReview.getImages().get(0)))
                .view(placeReview.getView())
                .createdAt(placeReview.getCreatedAt())
                .type("place")
                .build();
    }

    public static ReviewResponseDTO.SearchedReviewPreViewDTO toSearchedEventReviewPreviewDTO(EventReview eventReview) {
        return ReviewResponseDTO.SearchedReviewPreViewDTO.builder()
                .reviewId(eventReview.getId())
                .id(eventReview.getEvent().getId())
                .title(eventReview.getTitle())
                .content(eventReview.getContent())
                .reviewImage(eventReview.getImages().isEmpty() ? null :
                        ImageConverter.toImageDTO(eventReview.getImages().get(0)))
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
                .reviewImage(placeReview.getImages().isEmpty() ? null :
                        ImageConverter.toImageDTO(placeReview.getImages().get(0)))
                .view(placeReview.getView())
                .createdAt(placeReview.getCreatedAt())
                .type("place")
                .build();
    }

    public static ReviewResponseDTO.createdReviewDTO toCreatedReviewDTO(Long reviewId, String title) {
        return ReviewResponseDTO.createdReviewDTO.builder()
                .reviewId(reviewId)
                .title(title)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static EventReview toEventReview(ReviewRequestDTO.CreateDTO request, User user, Event event) {
        return EventReview.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .view(0L)
                .user(user)
                .event(event)
                .build();
    }

    public static PlaceReview toPlaceReview(ReviewRequestDTO.CreateDTO request, User user, Place place) {
        return PlaceReview.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .view(0L)
                .user(user)
                .place(place)
                .build();
    }
}