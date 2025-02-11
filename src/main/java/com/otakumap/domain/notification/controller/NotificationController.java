package com.otakumap.domain.notification.controller;

import com.otakumap.domain.auth.jwt.annotation.CurrentUser;
import com.otakumap.domain.notification.converter.NotificationConverter;
import com.otakumap.domain.notification.dto.NotificationResponseDTO;
import com.otakumap.domain.notification.entity.Notification;
import com.otakumap.domain.notification.service.NotificationQueryService;
import com.otakumap.domain.notification.service.NotificationCommandService;
import com.otakumap.domain.user.entity.User;
import com.otakumap.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationQueryService notificationQueryService;
    private final NotificationCommandService notificationCommandService;

    @GetMapping
    @Operation(summary = "알림 목록 조회 API", description = "읽지 않은 알림 목록을 조회합니다.")
    public ApiResponse<NotificationResponseDTO.NotificationListDTO> getUnreadNotifications(@CurrentUser User user) {
        List<Notification> notifications = notificationQueryService.getUnreadNotifications(user.getId());
        return ApiResponse.onSuccess(NotificationConverter.notificationListDTO(notifications));
    }

    @PatchMapping("/{notificationId}/read")
    @Operation(summary = "알림 읽음 처리 API", description = "알림을 읽음 처리합니다.")
    public ApiResponse<String> readNotification(
            @CurrentUser User user, @PathVariable Long notificationId) {
        notificationCommandService.markAsRead(user.getId(), notificationId);
        return ApiResponse.onSuccess("알림이 읽음처리 되었습니다.");
    }

    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @Operation(summary = "알림 전송", description = "알림을 전송합니다.")
    public ApiResponse<SseEmitter> subscribe(@CurrentUser User user,
                                             @RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId) {
        return ApiResponse.onSuccess(notificationCommandService.subscribe(user.getId(), lastEventId));
    }
}