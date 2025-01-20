package com.otakumap.domain.user.converter;

import com.otakumap.domain.auth.dto.*;
import com.otakumap.domain.user.entity.User;
import com.otakumap.domain.user.entity.enums.Role;
import com.otakumap.domain.user.entity.enums.SocialType;
import com.otakumap.domain.user.entity.enums.UserStatus;
import com.otakumap.global.util.UuidGenerator;

import java.time.LocalDateTime;

public class UserConverter {
    public static User toUser(AuthRequestDTO.SignupDTO request) {
        return User.builder()
                .name(request.getName())
                .nickname(request.getNickname())
                .userId(request.getUserId())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.USER)
                .status(UserStatus.ACTIVE)
                .build();
    }

    public static AuthResponseDTO.SignupResultDTO toSignupResultDTO(User user) {
        return AuthResponseDTO.SignupResultDTO.builder()
                .id(user.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static AuthResponseDTO.LoginResultDTO toLoginResultDTO(User user, String accessToken, String refreshToken) {
        return AuthResponseDTO.LoginResultDTO.builder()
                .id(user.getId())
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }

    public static AuthResponseDTO.CheckNicknameResultDTO toCheckNicknameResultDTO(boolean isDuplicated) {
        return AuthResponseDTO.CheckNicknameResultDTO.builder()
                .isDuplicated(isDuplicated)
                .build();
    }

    public static AuthResponseDTO.CheckIdResultDTO toCheckIdResultDTO(boolean isDuplicated) {
        return AuthResponseDTO.CheckIdResultDTO.builder()
                .isDuplicated(isDuplicated)
                .build();
    }

    public static AuthResponseDTO.VerifyCodeResultDTO toVerifyCodeResultDTO(boolean isVerified) {
        return AuthResponseDTO.VerifyCodeResultDTO.builder()
                .isVerified(isVerified)
                .build();
    }

    public static User toKakaoUser(KakaoUserInfo kakaoUserInfo) {
        return User.builder()
                .name(kakaoUserInfo.getKakao_account().getName())
                .nickname(UuidGenerator.generateUuid())
                .email(kakaoUserInfo.getKakao_account().getEmail())
                .socialType(SocialType.KAKAO)
                .build();
    }

    public static User toGoogleUser(GoogleUserInfo googleUserInfo) {
        return User.builder()
                .name(googleUserInfo.getName())
                .nickname(UuidGenerator.generateUuid())
                .email(googleUserInfo.getEmail())
                .socialType(SocialType.GOOGLE)
                .build();
    }

    public static User toNaverUser(NaverUserInfo naverUserInfo) {
        return User.builder()
                .name(naverUserInfo.getResponse().getName())
                .nickname(UuidGenerator.generateUuid())
                .email(naverUserInfo.getResponse().getEmail())
                .socialType(SocialType.NAVER)
                .build();
    }
}
