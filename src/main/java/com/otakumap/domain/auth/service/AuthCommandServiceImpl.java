package com.otakumap.domain.auth.service;

import com.otakumap.domain.auth.dto.AuthRequestDTO;
import com.otakumap.domain.user.converter.UserConverter;
import com.otakumap.domain.user.entity.User;
import com.otakumap.domain.user.repository.UserRepository;
import com.otakumap.global.apiPayload.code.status.ErrorStatus;
import com.otakumap.global.apiPayload.exception.handler.AuthHandler;
import com.otakumap.global.util.RedisUtil;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthCommandServiceImpl implements AuthCommandService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RedisUtil redisUtil;
    private final MailService mailService;

    @Override
    public User signup(AuthRequestDTO.SignupDTO request) {
        if(!request.getPassword().equals(request.getPasswordCheck())) {
            throw new AuthHandler(ErrorStatus.PASSWORD_NOT_EQUAL);
        }
        User newUser = UserConverter.toUser(request);
        newUser.encodePassword(passwordEncoder.encode(request.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public boolean checkNickname(AuthRequestDTO.CheckNicknameDTO request) {
        System.out.println(request.getNickname());
        return userRepository.existsByNickname(request.getNickname());
    }

    @Override
    public boolean checkId(AuthRequestDTO.CheckIdDTO request) {
        return userRepository.existsByUserId(request.getUserId());
    }

    @Override
    public String verifyEmail(AuthRequestDTO.VerifyEmailDTO request) throws MessagingException {
        try {
            mailService.sendEmail(request.getEmail());
        } catch (MailException e) {
            throw new AuthHandler(ErrorStatus.EMAIL_SEND_FAILED);
        }
        return "이메일 인증이 성공적으로 완료되었습니다.";
    }

    @Override
    public boolean verifyCode(AuthRequestDTO.VerifyCodeDTO request) {
        String authCode = redisUtil.getData(request.getEmail());
        if (authCode == null) {
            throw new AuthHandler(ErrorStatus.EMAIL_CODE_EXPIRED);
        }
        if (!authCode.equals(request.getCode())) {
            throw new AuthHandler(ErrorStatus.CODE_NOT_EQUAL);
        }
        return true;
    }
}
