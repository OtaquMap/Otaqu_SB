package com.otakumap.domain.route_like.controller;

import com.otakumap.domain.auth.jwt.annotation.CurrentUser;
import com.otakumap.domain.route_like.service.RouteLikeCommandService;
import com.otakumap.domain.user.entity.User;
import com.otakumap.global.apiPayload.ApiResponse;
import com.otakumap.global.validation.annotation.ExistRouteLike;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class RouteLikeController {

    private final RouteLikeCommandService routeLikeCommandService;

    @Operation(summary = "루트 저장", description = "루트를 저장합니다.")
    @PostMapping("/routes/{routeId}")
    @Parameters({
            @Parameter(name = "routeId", description = "루트 Id")
    })
    public ApiResponse<String> saveRouteLike(@PathVariable Long routeId, @CurrentUser User user) {

        routeLikeCommandService.saveRouteLike(user, routeId);

        return ApiResponse.onSuccess("루트가 성공적으로 저장되었습니다.");
    }

    @Operation(summary = "저장된 루트 삭제", description = "저장된 루트를 삭제합니다.")
    @DeleteMapping("/routes/liked")
    @Parameters({
            @Parameter(name = "routeIds", description = "저장된 루트 id List"),
    })
    public ApiResponse<String> deleteSavedRoute(@RequestParam(required = false) @ExistRouteLike List<Long> routeIds) {
        routeLikeCommandService.deleteRouteLike(routeIds);
        return ApiResponse.onSuccess("저장된 루트가 성공적으로 삭제되었습니다.");
    }

}
