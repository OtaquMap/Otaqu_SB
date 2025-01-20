package com.otakumap.domain.user.contoller;

import com.otakumap.domain.auth.jwt.annotation.CurrentUser;
import com.otakumap.domain.user.converter.UserConverter;
import com.otakumap.domain.user.dto.UserRequestDTO;
import com.otakumap.domain.user.dto.UserResponseDTO;
import com.otakumap.domain.user.entity.User;
import com.otakumap.domain.user.service.UserCommandService;
import com.otakumap.domain.user.service.UserQueryService;
import com.otakumap.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {
    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    @GetMapping("/users")
    @Operation(summary = "회원 정보 조회 API", description = "회원 정보를 조회합니다.")
    public ApiResponse<UserResponseDTO.UserInfoResponseDTO> getUserInfo(@CurrentUser User user) {
        User userInfo = userQueryService.getUserInfo(user.getId());
        return ApiResponse.onSuccess(UserConverter.toUserInfoResponseDTO(userInfo));
    }

    @PatchMapping("/users/nickname")
    @Operation(summary = "닉네임 변경 API", description = "회원의 닉네임을 변경합니다.")
    public ApiResponse<String> updateNickname(
            @CurrentUser User user,
            @RequestBody UserRequestDTO.UpdateNicknameDTO request) {
        userCommandService.updateNickname(user, request);
        return ApiResponse.onSuccess("닉네임이 성공적으로 수정되었습니다.");
    }
}
