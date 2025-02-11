package com.otakumap.domain.notification.service;

import com.otakumap.domain.notification.entity.enums.NotificationType;
import com.otakumap.domain.user.entity.User;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationCommandService {
    void markAsRead(Long userId, Long notificationId);
    SseEmitter subscribe(Long userId, String lastEventId);
    void send(User receiver, NotificationType notificationType, String content, String url);
}
