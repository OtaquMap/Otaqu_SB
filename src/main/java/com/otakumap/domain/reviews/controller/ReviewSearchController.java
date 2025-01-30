package com.otakumap.domain.reviews.controller;

import com.otakumap.domain.animation.entity.Animation;
import com.otakumap.domain.reviews.converter.ReviewConverter;
import com.otakumap.domain.reviews.dto.ReviewResponseDTO;
import com.otakumap.domain.reviews.service.ReviewQueryService;
import com.otakumap.global.apiPayload.ApiResponse;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.SearchHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewSearchController {

    private final ReviewQueryService reviewQueryService;

    @GetMapping("/reviews/top7")
    @Operation(summary = "조회수 Top7 여행 후기 목록 조회", description = "조회수 Top7 여행 후기 목록을 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })
    public ApiResponse<ReviewResponseDTO.Top7ReviewPreViewListDTO> getTop7ReviewList() {
        return null;
    }

    @GetMapping("/reviews/search")
    @Operation(summary = "키워드로 여행 후기 검색", description = "키워드로 여행 후기를 검색해서 조회합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
    })
    @Parameters({
            @Parameter(name = "keyword", description = "검색 키워드입니다."),
            @Parameter(name = "page", description = "페이지 번호 (0부터 시작)", example = "0"),
            @Parameter(name = "size", description = "한 페이지당 최대 리뷰 수", example = "10"),
            @Parameter(name = "sort", description = "정렬 기준 (latest 또는 views)", example = "latest")
    })
    public ApiResponse<Page<ReviewResponseDTO.SearchedReviewPreViewDTO>> getSearchedReviewList(@RequestParam String keyword,
                                                                                               @RequestParam(defaultValue = "0") int page,
                                                                                               @RequestParam(defaultValue = "10") int size,
                                                                                               @RequestParam(defaultValue = "latest") String sort) {

        Page<ReviewResponseDTO.SearchedReviewPreViewDTO> searchResults = reviewQueryService.searchReviewsByKeyword(keyword, page, size, sort);

        return ApiResponse.onSuccess(searchResults);
    }

    @GetMapping("/reviews/animations/search")
    @Operation(summary = "애니메이션 검색", description = "키워드로 애니메이션 제목을 검색해서 조회합니다.")
    public ApiResponse<ReviewResponseDTO.AnimationResultListDTO> searchAnimation(
            @RequestParam @NotBlank(message = "검색어를 입력해주세요") @Pattern(regexp = "^\\S+$", message = "공백은 허용되지 않습니다") String keyword) {
        List<Animation> animationList = reviewQueryService.searchAnimation(keyword);

        if (animationList.isEmpty()) {
            throw new SearchHandler(ErrorStatus.ANIMATION_NOT_FOUND);
        }

        return ApiResponse.onSuccess(ReviewConverter.animationResultListDTO(animationList));
    }
}
