package com.otakumap.domain.notification.service;

public interface NotificationCommandService {
    void markAsRead(Long userId, Long notificationId);
}
