package com.otakumap.domain.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserInfoResponseDTO {
        private String profileImageUrl;
        private String nickname;
        private String email;
        private Integer donation;
        private boolean community_activity;
        private boolean event_benefits_info;
    }
}
