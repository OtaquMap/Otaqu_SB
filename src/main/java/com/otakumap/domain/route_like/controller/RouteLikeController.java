package com.otakumap.domain.route_like.controller;

import com.otakumap.domain.route_like.service.RouteLikeCommandService;
import com.otakumap.global.apiPayload.ApiResponse;
import com.otakumap.global.validation.annotation.ExistRouteLike;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Validated
public class RouteLikeController {

    private final RouteLikeCommandService routeLikeCommandService;

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
