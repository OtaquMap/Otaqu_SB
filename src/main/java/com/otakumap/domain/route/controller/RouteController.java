package com.otakumap.domain.route.controller;

import com.otakumap.domain.auth.jwt.annotation.CurrentUser;
import com.otakumap.domain.place.DTO.PlaceResponseDTO;
import com.otakumap.domain.place.service.PlaceQueryService;
import com.otakumap.domain.route.dto.RouteResponseDTO;
import com.otakumap.domain.route.service.RouteQueryService;
import com.otakumap.domain.user.entity.User;
import com.otakumap.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/route")
@RequiredArgsConstructor
@Validated
public class RouteController {
    private final PlaceQueryService placeQueryService;
    private final RouteQueryService routeQueryService;

    @Operation(summary = "루트 내 특정 장소 상세 정보 조회", description = "주어진 routeId와 placeId를 기반으로 특정 장소의 상세 정보를 불러옵니다.")
    @GetMapping("{routeId}/{placeId}")
    @Parameters({
            @Parameter(name = "routeId", description = "루트 ID"),
            @Parameter(name = "placeId", description = "루트 내 특정 장소 ID")
    })
    public ApiResponse<PlaceResponseDTO.PlaceDetailDTO> getPlaceDetail(
            @CurrentUser User user,
            @PathVariable Long routeId,
            @PathVariable Long placeId) {
        PlaceResponseDTO.PlaceDetailDTO placeDetail = placeQueryService.getPlaceDetail(user, routeId, placeId);
        return ApiResponse.onSuccess(placeDetail);
    }

    @Operation(summary = "루트 상세 정보 조회", description = "주어진 routeId를 기반으로 루트의 상세 정보를 불러옵니다.")
    @GetMapping("{routeId}")
    @Parameters({
            @Parameter(name = "routeId", description = "루트 ID")
    })
    public ApiResponse<RouteResponseDTO.RouteDetailDTO> getRouteDetail(
            @CurrentUser User user,
            @PathVariable Long routeId) {
        RouteResponseDTO.RouteDetailDTO routeDetail = routeQueryService.getRouteDetail(user, routeId);
        return ApiResponse.onSuccess(routeDetail);
    }
}
