package com.otakumap.domain.place_like.controller;

import com.otakumap.domain.auth.jwt.annotation.CurrentUser;
import com.otakumap.domain.place_like.dto.PlaceLikeResponseDTO;
import com.otakumap.domain.place_like.service.PlaceLikeCommandService;
import com.otakumap.domain.place_like.service.PlaceLikeQueryService;
import com.otakumap.domain.user.entity.User;
import com.otakumap.global.apiPayload.ApiResponse;
import com.otakumap.global.validation.annotation.ExistPlaceLike;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/place-likes")
@RequiredArgsConstructor
@Validated
public class PlaceLikeController {
    private final PlaceLikeQueryService placeLikeQueryService;
    private final PlaceLikeCommandService placeLikeCommandService;

    @Operation(summary = "저장된 장소 목록 조회", description = "저장된 장소 목록을 불러옵니다.")
    @GetMapping("")
    @Parameters({
            @Parameter(name = "lastId", description = "마지막으로 조회된 저장된 이벤트 id, 처음 가져올 때 -> 0"),
            @Parameter(name = "limit", description = "한 번에 조회할 최대 이벤트 수. 기본값은 10입니다.")
    })
    public ApiResponse<PlaceLikeResponseDTO.PlaceLikePreViewListDTO> getPlaceLikeList(@CurrentUser User user, @RequestParam(defaultValue = "0") Long lastId, @RequestParam(defaultValue = "10") int limit) {
        return ApiResponse.onSuccess(placeLikeQueryService.getPlaceLikeList(user, lastId, limit));
    }

    @Operation(summary = "저장된 장소 삭제", description = "저장된 장소를 삭제합니다.")
    @DeleteMapping("")
    @Parameters({
            @Parameter(name = "placeIds", description = "저장된 장소 id List"),
    })
    public ApiResponse<String> deletePlaceLike(@RequestParam(required = false) @ExistPlaceLike List<Long> placeIds) {
        placeLikeCommandService.deletePlaceLike(placeIds);
        return ApiResponse.onSuccess("저장된 장소가 성공적으로 삭제되었습니다");
    }

    @Operation(summary = "장소 저장", description = "장소를 저장합니다.")
    @PostMapping("/{placeId}")
    @Parameters({
            @Parameter(name = "placeId", description = "장소 Id")
    })
    public ApiResponse<String> savePlaceLike(@PathVariable Long placeId, @CurrentUser User user) {

         placeLikeCommandService.savePlaceLike(user, placeId);

         return ApiResponse.onSuccess("장소가 성공적으로 저장되었습니다.");
    }
}
