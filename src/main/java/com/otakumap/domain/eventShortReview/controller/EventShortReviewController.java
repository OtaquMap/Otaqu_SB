package com.otakumap.domain.eventShortReview.controller;

import com.otakumap.domain.eventShortReview.converter.EventShortReviewConverter;
import com.otakumap.domain.eventShortReview.dto.EventShortReviewRequestDTO;
import com.otakumap.domain.eventShortReview.dto.EventShortReviewResponseDTO;
import com.otakumap.domain.eventShortReview.entity.EventShortReview;
import com.otakumap.domain.eventShortReview.repository.EventShortReviewRepository;
import com.otakumap.domain.eventShortReview.service.EventShortReviewCommandService;
import com.otakumap.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class EventShortReviewController {

    private final EventShortReviewCommandService eventShortReviewCommandService;
    private final EventShortReviewRepository eventShortReviewRepository;

    @Operation(summary = "이벤트 한 줄 리뷰 작성", description = "이벤트에 한 줄 리뷰를 작성합니다.")
    @PostMapping("/events/{eventId}/short-reviews")
    @Parameters({
            @Parameter(name = "eventId", description = "특정 이벤트의 Id")
    })
    public ApiResponse<EventShortReviewResponseDTO.NewEventShortReviewDTO> createEventShortReview(@PathVariable Long eventId,
                                                                                                  @RequestBody EventShortReviewRequestDTO.NewEventShortReviewDTO request) {
        EventShortReview eventShortReview = eventShortReviewCommandService.createEventShortReview(eventId, request);
        return ApiResponse.onSuccess(EventShortReviewConverter.toNewEventShortReviewDTO(eventShortReview));
    }

    @Operation(summary = "이벤트 한 줄 리뷰 목록 조회", description = "특정 이벤트의 한 줄 리뷰 목록을 불러옵니다.")
    @GetMapping("/events/{eventId}/short-reviews")
    @Parameters({
            @Parameter(name = "eventId", description = "특정 이벤트의 Id")
    })
    public ApiResponse<EventShortReviewResponseDTO.EventShortReviewListDTO> getEventShortReviewList(@PathVariable(name = "eventId") Long eventId, @RequestParam(name = "page")Integer page) {
        return ApiResponse.onSuccess(EventShortReviewConverter.toEventShortReviewListDTO(eventShortReviewCommandService.getEventShortReviewsByEventId(eventId, page)));
    }
}
