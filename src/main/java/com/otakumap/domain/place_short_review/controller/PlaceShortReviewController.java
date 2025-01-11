package com.otakumap.domain.place_short_review.controller;

import com.otakumap.domain.place_short_review.converter.PlaceShortReviewConverter;
import com.otakumap.domain.place_short_review.dto.PlaceShortReviewRequestDTO;
import com.otakumap.domain.place_short_review.dto.PlaceShortReviewResponseDTO;
import com.otakumap.domain.place_short_review.entity.PlaceShortReview;
import com.otakumap.domain.place_short_review.service.PlaceShortReviewCommandService;
import com.otakumap.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/places")
public class PlaceShortReviewController {
    private final PlaceShortReviewCommandService placeShortReviewCommandService;

    @PostMapping("/{placeId}/short-review")
    public ApiResponse<PlaceShortReviewResponseDTO.CreateReviewDTO> createReview(
            @PathVariable Long placeId,
            // TODO: place id validation
            @RequestBody @Valid PlaceShortReviewRequestDTO.CreateDTO request) {
        PlaceShortReview placeShortReview = placeShortReviewCommandService.createReview(placeId, request);
        return ApiResponse.onSuccess(PlaceShortReviewConverter.toCreateReviewDTO(placeShortReview));
    }
}
