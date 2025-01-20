package com.otakumap.domain.user.dto;

import lombok.Getter;

public class UserRequestDTO {
    @Getter
    public static class UpdateNicknameDTO {
        private String nickname;
    }
}
