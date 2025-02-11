package com.otakumap.domain.event.controller;

import com.otakumap.domain.event.dto.EventResponseDTO;
import com.otakumap.domain.event.service.EventCustomService;
import com.otakumap.domain.event.service.EventQueryService;
import com.otakumap.domain.image.dto.ImageResponseDTO;
import com.otakumap.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class EventController {

    private final EventQueryService eventQueryService;
    private final EventCustomService eventCustomService;

    @Operation(summary = "진행 중인 인기 이벤트 조회", description = "진행 중인 인기 이벤트의 목록(8개)를 불러옵니다.")
    @GetMapping("/events/popular")
    public ApiResponse<List<EventResponseDTO.EventDTO>> getEventDetail() {
        return ApiResponse.onSuccess(eventCustomService.getPopularEvents());
    }

    @Operation(summary = "이벤트 상세 정보 조회", description = "특정 이벤트의 상세 정보를 불러옵니다.")
    @GetMapping("/events/{eventId}/details")
    @Parameter(name = "eventId", description = "이벤트 Id")
    public ApiResponse<EventResponseDTO.EventDetailDTO> getEventDetail(@PathVariable Long eventId) {
        return ApiResponse.onSuccess(eventQueryService.getEventDetail(eventId));
    }

    @Operation(summary = "홈 화면 이벤트 배너 조회", description = "홈 화면에 띄울 배너 이미지를 불러옵니다.")
    @GetMapping("/events/banner")
    public ApiResponse<ImageResponseDTO.ImageDTO> getBanner() {
        return ApiResponse.onSuccess(eventCustomService.getEventBanner());
    }
}
