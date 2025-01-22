package com.otakumap.domain.auth.service;

import com.otakumap.domain.auth.dto.AuthRequestDTO;
import com.otakumap.domain.auth.dto.AuthResponseDTO;
import com.otakumap.domain.auth.jwt.dto.JwtDTO;
import com.otakumap.domain.user.entity.User;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthCommandService {
    User signup(AuthRequestDTO.SignupDTO request);
    AuthResponseDTO.LoginResultDTO login(AuthRequestDTO.LoginDTO request);
    boolean checkId(AuthRequestDTO.CheckIdDTO request);
    void verifyEmail(AuthRequestDTO.VerifyEmailDTO request) throws MessagingException;
    boolean verifyCode(AuthRequestDTO.VerifyCodeDTO request);
    JwtDTO reissueToken(String refreshToken);
    void logout(HttpServletRequest request);
}
