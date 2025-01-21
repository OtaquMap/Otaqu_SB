package com.otakumap.domain.notification.controller;

import com.otakumap.domain.auth.jwt.annotation.CurrentUser;
import com.otakumap.domain.notification.converter.NotificationConverter;
import com.otakumap.domain.notification.dto.NotificationResponseDTO;
import com.otakumap.domain.notification.entity.Notification;
import com.otakumap.domain.notification.service.NotificationQueryService;
import com.otakumap.domain.user.entity.User;
import com.otakumap.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notifications")
public class NotificationController {
    private final NotificationQueryService notificationQueryService;

    @GetMapping
    @Operation(summary = "알림 목록 조회 API", description = "알림 목록을 조회합니다.")
    public ApiResponse<NotificationResponseDTO.NotificationListDTO> getNotifications(@CurrentUser User user) {
        List<Notification> notifications = notificationQueryService.getNotifications(user.getId());
        return ApiResponse.onSuccess(NotificationConverter.notificationListDTO(notifications));
    }
}