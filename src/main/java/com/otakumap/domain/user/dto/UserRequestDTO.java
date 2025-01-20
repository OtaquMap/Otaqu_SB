package com.otakumap.domain.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class UserRequestDTO {
    @Getter
    public static class UpdateNicknameDTO {
        @NotNull
        private String nickname;
    }
}
