package com.otakumap.domain.notification.service;

import com.otakumap.domain.notification.dto.NotificationResponseDTO;
import com.otakumap.domain.notification.entity.Notification;
import com.otakumap.domain.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationQueryServiceImpl implements NotificationQueryService {
    private final NotificationRepository notificationRepository;

    @Override
    public List<Notification> getNotifications(Long userId) {
        List<Notification> notifications = notificationRepository.findAllByUserId(userId);
        return notifications;
    }
}
