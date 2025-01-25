package com.otakumap.domain.user.service;

import com.otakumap.domain.user.converter.UserConverter;
import com.otakumap.domain.user.dto.UserRequestDTO;
import com.otakumap.domain.user.entity.User;
import com.otakumap.domain.user.repository.UserRepository;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.AuthHandler;
import com.otakumap.global.apiPayload.exception.handler.UserHandler;
import com.otakumap.global.util.EmailUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final EmailUtil emailUtil;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void updateNickname(User user, UserRequestDTO.UpdateNicknameDTO request) {
        if (userRepository.existsByNickname(request.getNickname())) {
            throw new UserHandler(ErrorStatus.NICKNAME_ALREADY_EXISTS);
        }
        user.setNickname(request.getNickname());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void reportEvent(UserRequestDTO.UserReportRequestDTO request) {
        String subject = "[이벤트 제보] " + request.getEventName();
        String content = String.format(
                "이벤트명: %s\n애니메이션명: %s\n추가사항: %s\n",
                request.getEventName(),
                request.getAnimationName(),
                request.getAdditionalInfo() == null ? "없음" : request.getAdditionalInfo()
        );

        // 이메일 전송
        emailUtil.sendEmail("otakumap0123@gmail.com", subject, content);
    }

    @Override
    @Transactional
    public void updateNotificationSettings(User user, UserRequestDTO.NotificationSettingsRequestDTO request) {
        if (request.getNotificationType() != 1 && request.getNotificationType() != 2) {
            throw new UserHandler(ErrorStatus.INVALID_NOTIFICATION_TYPE);
        }
        user.setNotification(request.getNotificationType(), request.isEnabled());
        userRepository.save(user);
    }

    @Override
    public void resetPassword(UserRequestDTO.ResetPasswordDTO request) {
        User user = userRepository.findByUserId(request.getUserId()).orElseThrow(() -> new UserHandler(ErrorStatus.USER_NOT_FOUND));

        if(!request.getPassword().equals(request.getPasswordCheck())) {
            throw new AuthHandler(ErrorStatus.PASSWORD_NOT_EQUAL);
        }

        user.encodePassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
    }
}