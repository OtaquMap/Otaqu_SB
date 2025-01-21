package com.otakumap.domain.notification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class NotificationResponseDTO {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NotificationListDTO {
        List<NotificationDTO> notifications;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NotificationDTO {
        Long id;
        String type;
        String message;
        LocalDateTime createdAt;
        boolean isRead;
    }
}
