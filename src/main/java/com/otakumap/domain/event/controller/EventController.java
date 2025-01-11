package com.otakumap.domain.event.controller;

import com.otakumap.domain.event.dto.EventResponseDTO;
import com.otakumap.domain.event.service.EventQueryService;
import com.otakumap.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class EventController {

    private final EventQueryService eventQueryService;

    @Operation(summary = "이벤트 상세 정보 조회", description = "특정 이벤트의 상세 정보를 불러옵니다.")
    @GetMapping("/events/{eventId}/details")
    @Parameter(name = "eventId", description = "이벤트 Id")
    public ApiResponse<EventResponseDTO.EventDetailDTO> getEventDetail(@PathVariable Long eventId) {
        return ApiResponse.onSuccess(eventQueryService.getEventDetail(eventId));
    }
}
