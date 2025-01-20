package com.otakumap.domain.route_like.controller;

import com.otakumap.domain.auth.jwt.annotation.CurrentUser;
import com.otakumap.domain.route_like.service.RouteLikeCommandService;
import com.otakumap.domain.user.entity.User;
import com.otakumap.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class RouteLikeController {

    private final RouteLikeCommandService routeLikeCommandService;

    @Operation(summary = "루트 저장", description = "루트를 저장합니다.")
    @PostMapping("/routes/{routeId}/save")
    @Parameters({
            @Parameter(name = "routeId", description = "루트 Id")
    })
    public ApiResponse<String> saveRouteLike(@PathVariable Long routeId, @CurrentUser User user) {

        routeLikeCommandService.saveRouteLike(user, routeId);

        return ApiResponse.onSuccess("루트가 성공적으로 저장되었습니다.");
    }


}
