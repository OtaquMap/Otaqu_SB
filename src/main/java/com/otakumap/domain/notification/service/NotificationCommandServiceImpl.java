package com.otakumap.domain.notification.service;

import com.otakumap.domain.notification.entity.Notification;
import com.otakumap.domain.notification.repository.NotificationRepository;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.NotificationHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationCommandServiceImpl implements NotificationCommandService{
    private final NotificationRepository notificationRepository;

    @Override
    @Transactional
    public void markAsRead(Long userId, Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotificationHandler(ErrorStatus.NOTIFICATION_NOT_FOUND));

        if (!notification.getUser().getId().equals(userId)) {
            throw new NotificationHandler(ErrorStatus.NOTIFICATION_ACCESS_DENIED);
        }

        if (notification.isRead()) {
            throw new NotificationHandler(ErrorStatus.NOTIFICATION_ALREADY_READ);
        }

        notification.setIsRead(true);
        notificationRepository.save(notification);
    }
}
