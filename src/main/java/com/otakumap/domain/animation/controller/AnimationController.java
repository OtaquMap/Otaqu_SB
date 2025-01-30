package com.otakumap.domain.animation.controller;

import com.otakumap.domain.animation.DTO.AnimationResponseDTO;
import com.otakumap.domain.animation.converter.AnimationConverter;
import com.otakumap.domain.animation.entity.Animation;
import com.otakumap.domain.animation.service.AnimationQueryService;
import com.otakumap.global.apiPayload.ApiResponse;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.SearchHandler;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/animations")
public class AnimationController {
    private final AnimationQueryService animationQueryService;

    @GetMapping("/search")
    @Operation(summary = "애니메이션 검색", description = "키워드로 애니메이션 제목을 검색해서 조회합니다. 공백은 허용되지 않습니다.")
    public ApiResponse<AnimationResponseDTO.AnimationResultListDTO> searchAnimation(
            @RequestParam @NotBlank(message = "검색어를 입력해주세요")
            @Pattern(regexp = "^[^\\s].*$", message = "첫 글자는 공백이 될 수 없습니다") String keyword) {
        List<Animation> animationList = animationQueryService.searchAnimation(keyword);

        if (animationList.isEmpty()) {
            throw new SearchHandler(ErrorStatus.ANIMATION_NOT_FOUND);
        }

        return ApiResponse.onSuccess(AnimationConverter.animationResultListDTO(animationList));
    }
}
