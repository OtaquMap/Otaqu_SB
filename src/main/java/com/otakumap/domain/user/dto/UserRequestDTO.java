package com.otakumap.domain.user.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;

public class UserRequestDTO {
    @Getter
    public static class UpdateNicknameDTO {
        @NotBlank
        @Size(min = 1, max = 20, message = "닉네임은 1자 이상 20자 이하로 입력해주세요.")
        private String nickname;
    }

    @Getter
    public static class UserReportRequestDTO {
        @NotBlank(message = "이벤트명을 입력해주세요.")
        private String eventName;

        @NotBlank(message = "애니메이션명을 입력해주세요.")
        private String animationName;

        private String additionalInfo;
    }

    @Getter
    public static class NotificationSettingsRequestDTO {
        @NotNull
        @Min(value = 1, message = "알림 타입은 1 또는 2로 입력해주세요.")
        @Max(value = 2, message = "알림 타입은 1 또는 2로 입력해주세요.")
        private Integer notificationType;

        @NotNull
        private boolean isEnabled;
    }
}
