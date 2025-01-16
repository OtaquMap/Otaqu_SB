package com.otakumap.domain.auth.controller;

import com.otakumap.domain.auth.dto.AuthRequestDTO;
import com.otakumap.domain.auth.dto.AuthResponseDTO;
import com.otakumap.domain.auth.service.AuthCommandService;
import com.otakumap.domain.user.converter.UserConverter;
import com.otakumap.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthCommandService authCommandService;

    @Operation(summary = "회원가입", description = "회원가입 기능입니다.")
    @PostMapping("/signup")
    public ApiResponse<AuthResponseDTO.SignupResultDTO> signup(@RequestBody @Valid AuthRequestDTO.SignupDTO request) {
        return ApiResponse.onSuccess(UserConverter.toSignupResultDTO(authCommandService.signup(request)));
    }

    @Operation(summary = "닉네임 중복 확인", description = "닉네임 중복 확인 기능입니다.")
    @PostMapping("/check-nickname")
    public ApiResponse<AuthResponseDTO.CheckNicknameResultDTO> checkNickname(@RequestBody @Valid AuthRequestDTO.CheckNicknameDTO request) {
        return ApiResponse.onSuccess(UserConverter.toCheckNicknameResultDTO(authCommandService.checkNickname(request)));
    }

    @Operation(summary = "아이디 중복 확인", description = "아이디 중복 확인 기능입니다.")
    @PostMapping("/check-id")
    public ApiResponse<AuthResponseDTO.CheckIdResultDTO> checkId(@RequestBody @Valid AuthRequestDTO.CheckIdDTO request) {
        return ApiResponse.onSuccess(UserConverter.toCheckIdResultDTO(authCommandService.checkId(request)));
    }

    @Operation(summary = "이메일 인증 메일 전송", description = "이메일 인증을 위한 메일 전송 기능입니다.")
    @PostMapping("/verify-email")
    public ApiResponse<String> verifyEmail(@RequestBody @Valid AuthRequestDTO.VerifyEmailDTO request) throws MessagingException {
        return ApiResponse.onSuccess(authCommandService.verifyEmail(request));
    }

    @Operation(summary = "이메일 코드 인증", description = "이메일 코드 인증 기능입니다.")
    @PostMapping("/verify-code")
    public ApiResponse<AuthResponseDTO.VerifyCodeResultDTO> verifyEmail(@RequestBody @Valid AuthRequestDTO.VerifyCodeDTO request) {
        return ApiResponse.onSuccess(UserConverter.toVerifyCodeResultDTO(authCommandService.verifyCode(request)));
    }
}
