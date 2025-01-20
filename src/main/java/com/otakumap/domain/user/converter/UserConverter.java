package com.otakumap.domain.user.converter;

import com.otakumap.domain.auth.dto.AuthRequestDTO;
import com.otakumap.domain.auth.dto.AuthResponseDTO;
import com.otakumap.domain.user.dto.UserResponseDTO;
import com.otakumap.domain.user.entity.User;
import com.otakumap.domain.user.entity.enums.Role;
import com.otakumap.domain.user.entity.enums.UserStatus;

import java.time.LocalDateTime;

public class UserConverter {
    public static User toUser(AuthRequestDTO.SignupDTO request) {
        return User.builder()
                .name(request.getName())
                .nickname(request.getNickname())
                .userId(request.getUserId())
                .email(request.getEmail())
                .password(request.getPassword())
                .isCommunityActivityNotified(true)
                .isEventBenefitsNotified(true)
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

    public static UserResponseDTO.UserInfoResponseDTO toUserInfoResponseDTO(User user) {
        return UserResponseDTO.UserInfoResponseDTO.builder()
                .profileImageUrl(user.getProfileImage() == null ? null : user.getProfileImage().getFileUrl())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .donation(user.getDonation())
                .community_activity(user.getIsCommunityActivityNotified())
                .event_benefits_info(user.getIsEventBenefitsNotified())
                .build();
    }
}
