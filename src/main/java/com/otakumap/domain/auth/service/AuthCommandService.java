package com.otakumap.domain.auth.service;

import com.otakumap.domain.auth.dto.AuthRequestDTO;
import com.otakumap.domain.user.entity.User;
import jakarta.mail.MessagingException;

public interface AuthCommandService {
    User signup(AuthRequestDTO.SignupDTO request);
    boolean checkNickname(AuthRequestDTO.CheckNicknameDTO request);
    boolean checkId(AuthRequestDTO.CheckIdDTO request);
    String verifyEmail(AuthRequestDTO.VerifyEmailDTO request) throws MessagingException;
    boolean verifyCode(AuthRequestDTO.VerifyCodeDTO request);
}
