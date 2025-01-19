package com.otakumap.domain.user.contoller;

import com.otakumap.domain.auth.jwt.annotation.CurrentUser;
import com.otakumap.domain.user.converter.UserConverter;
import com.otakumap.domain.user.dto.UserResponseDTO;
import com.otakumap.domain.user.entity.User;
import com.otakumap.domain.user.service.UserQueryService;
import com.otakumap.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserQueryService userQueryService;

    @GetMapping("/users")
    @Operation(summary = "회원 정보 조회 API", description = "회원 정보를 조회합니다.")
    public ApiResponse<UserResponseDTO.UserInfoResponseDTO> getUserInfo(@CurrentUser User user) {
        User userInfo = userQueryService.getUserInfo(user.getId());
        return ApiResponse.onSuccess(UserConverter.toUserInfoResponseDTO(userInfo));
    }
}
