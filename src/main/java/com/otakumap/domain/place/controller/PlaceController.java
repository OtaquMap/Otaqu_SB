package com.otakumap.domain.place.controller;

import com.otakumap.domain.auth.jwt.annotation.CurrentUser;
import com.otakumap.domain.place.dto.PlaceResponseDTO;
import com.otakumap.domain.place.service.PlaceService;
import com.otakumap.domain.user.entity.User;
import com.otakumap.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/map")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @GetMapping("/saved-places")
    @Operation(summary = "저장된 장소 조회 API", description = "현재 사용자가 저장한 장소 목록을 조회합니다.")
    public ApiResponse<List<PlaceResponseDTO>> getSavedPlaces(@CurrentUser User user) {
        // 인증된 사용자 정보를 기반으로 저장된 장소 조회
        List<PlaceResponseDTO> savedPlaces = placeService.getSavedPlaces(user.getId());
        return ApiResponse.onSuccess(savedPlaces);
    }
}
