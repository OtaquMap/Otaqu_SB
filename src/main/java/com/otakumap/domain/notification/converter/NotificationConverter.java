package com.otakumap.domain.notification.converter;

import com.otakumap.domain.notification.dto.NotificationResponseDTO;
import com.otakumap.domain.notification.entity.Notification;

import java.util.List;
import java.util.stream.Collectors;

public class NotificationConverter {
    public static NotificationResponseDTO.NotificationDTO notificationDTO(Notification notification) {
        return NotificationResponseDTO.NotificationDTO.builder()
                .id(notification.getId())
                .type(notification.getType().name())
                .message(notification.getMessage())
                .url(notification.getUrl())
                .createdAt(notification.getCreatedAt())
                .isRead(notification.isRead())
                .build();
    }

    public static NotificationResponseDTO.NotificationListDTO notificationListDTO(List<Notification> notifications) {
        return NotificationResponseDTO.NotificationListDTO.builder()
                .notifications(notifications.stream().map(NotificationConverter::notificationDTO).collect(Collectors.toList()))
                .build();
    }
}
