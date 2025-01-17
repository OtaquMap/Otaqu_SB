package com.otakumap.domain.auth.service;

import com.otakumap.global.util.RedisUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;

    private final RedisUtil redisUtil;

    @Value("${spring.mail.username}")
    private String senderEmail;

    // 인증 코드 생성
    private String createCode() {
        int leftLimit = 48; // number '0'
        int rightLimit = 122; // alphabet 'z'
        int targetStringLength = 6;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 | i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    // 이메일 폼 생성
    private MimeMessage createEmailForm(String email, String code) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();

        message.setFrom(senderEmail);
        message.addRecipients(MimeMessage.RecipientType.TO, email);

        message.setSubject("오타쿠맵 이메일 인증 안내");
        String body = "";
        body += "<p>회원가입을 위한 이메일 인증을 진행합니다.</p>";
        body += "<p>아래 발급된 인증번호를 입력하여 인증을 완료해주세요.</p>";
        body += "<br>";
        body += "<p>인증번호 " + code + "</p>";
        message.setText(body, "UTF-8", "html");

        // Redis에 해당 인증코드 인증 시간 설정(30분)
        redisUtil.set("auth:" + email, code);
        redisUtil.expire("auth:" + email, 30 * 60 * 1000L, TimeUnit.MILLISECONDS);

        return message;
    }

    // 메일 발송
    @Async
    public void sendEmail(String sendEmail) throws MessagingException {
        if (redisUtil.exists("auth:" + sendEmail)) {
            redisUtil.delete("auth:" + sendEmail);
        }
        String authCode = createCode();
        MimeMessage message = createEmailForm(sendEmail, authCode); // 메일 생성
        try {
            javaMailSender.send(message); // 메일 발송
        } catch (MailException e) {
            throw new MailSendException(e.getMessage());
        }
    }
}
