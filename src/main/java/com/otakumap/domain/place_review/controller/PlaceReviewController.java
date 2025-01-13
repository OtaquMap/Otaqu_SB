package com.otakumap.domain.place_review.controller;

import com.otakumap.domain.place_review.converter.PlaceReviewConverter;
import com.otakumap.domain.place_review.dto.PlaceReviewRequestDTO;
import com.otakumap.domain.place_review.dto.PlaceReviewResponseDTO;
import com.otakumap.domain.place_review.entity.PlaceReview;
import com.otakumap.domain.place_review.service.PlaceReviewCommandService;
import com.otakumap.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PlaceReviewController {
    private final PlaceReviewCommandService placeReviewCommandService;

    @PostMapping("/review")
    @Operation(summary = "리뷰 작성")
    public ApiResponse<PlaceReviewResponseDTO.ReviewCreateResponseDTO> createReview(@RequestBody @Valid PlaceReviewRequestDTO.ReviewCreateRequestDTO request) {
        PlaceReview placeReview = placeReviewCommandService.createReview(request);
        return ApiResponse.onSuccess(PlaceReviewConverter.toReviewCreateResponseDTO(placeReview));
    }
}
