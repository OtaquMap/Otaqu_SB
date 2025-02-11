package com.otakumap.domain.notification.service;

import com.otakumap.domain.notification.entity.Notification;
import com.otakumap.domain.notification.entity.enums.NotificationType;
import com.otakumap.domain.notification.repository.EmitterRepository;
import com.otakumap.domain.notification.repository.NotificationRepository;
import com.otakumap.domain.user.entity.User;
import com.otakumap.global.apiPayload.ApiResponse;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.NotificationHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class NotificationCommandServiceImpl implements NotificationCommandService {
    private final NotificationRepository notificationRepository;
    private final EmitterRepository emitterRepository;

    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 60; // 1 hour

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

    @Override
    @Transactional
    public SseEmitter subscribe(Long userId, String lastEventId) {
        String emitterId = userId + "_" + System.currentTimeMillis();
        SseEmitter emitter = emitterRepository.save(emitterId, new SseEmitter(DEFAULT_TIMEOUT));

        emitter.onCompletion(() -> emitterRepository.deleteById(emitterId));
        emitter.onTimeout(() -> emitterRepository.deleteById(emitterId));

        sendToClient(emitter, emitterId, "EventStream Created. [userId=" + userId + "]");

        if (!lastEventId.isEmpty()) {
            Map<String, Object> events = emitterRepository.findAllEventCacheStartWithByUserId(String.valueOf(userId));
            events.entrySet().stream()
                    .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0)
                    .forEach(entry -> sendToClient(emitter, entry.getKey(), entry.getValue()));
        }
        return emitter;
    }

    @Override
    @Transactional
    public void send(User receiver, NotificationType notificationType, String content, String url) {
        Notification notification = createNotification(receiver, notificationType, content, url);
        notificationRepository.save(notification);

        String memberId = String.valueOf(receiver.getId());

        Map<String, SseEmitter> sseEmitters = emitterRepository.findAllEmitterStartWithByUserId(memberId);
        sseEmitters.forEach((key, emitter) -> {
            emitterRepository.saveEventCache(key, notification);
            sendToClient(emitter, key, ApiResponse.onSuccess(notification));
        });
    }

    private Notification createNotification(User user, NotificationType notificationType, String message, String url) {
        return Notification.builder()
                .user(user)
                .type(notificationType)
                .message(message)
                .url(url)
                .isRead(false)
                .build();
    }

    private void sendToClient(SseEmitter emitter, String emitterId, Object data) {
        try {
            emitter.send(SseEmitter.event().id(emitterId).data(data));
        } catch (IOException exception) {
            emitterRepository.deleteById(emitterId);
            exception.printStackTrace();
        }
    }
}