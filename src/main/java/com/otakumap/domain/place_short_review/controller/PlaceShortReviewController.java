package com.otakumap.domain.place_short_review.controller;

import com.otakumap.domain.place_short_review.DTO.PlaceShortReviewResponseDTO;
import com.otakumap.domain.place_short_review.converter.PlaceShortReviewConverter;
import com.otakumap.domain.place_short_review.service.PlaceShortReviewQueryService;
import com.otakumap.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/places")
public class PlaceShortReviewController {


    private final PlaceShortReviewQueryService placeShortReviewQueryService;

    @GetMapping("/{placeId}/short-review")
    @Operation(summary = "특정 명소의 한 줄 리뷰 목록 조회 API", description = "특정 명소의 한 줄 리뷰 목록을 조회하는 API이며, 페이징을 포함합니다. query string으로 page 번호를 함께 보내주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })
    @Parameters({
            @Parameter(name = "placeId", description = "명소의 아이디입니다.")
    })
    public ApiResponse<PlaceShortReviewResponseDTO.PlaceShortReviewListDTO> getPlaceShortReviewList(@PathVariable(name = "placeId") Long placeId, @RequestParam(name = "page") Integer page){
        return ApiResponse.onSuccess(PlaceShortReviewConverter.placeShortReviewListDTO(placeShortReviewQueryService.getPlaceShortReviews(placeId, page)));
    }
}
