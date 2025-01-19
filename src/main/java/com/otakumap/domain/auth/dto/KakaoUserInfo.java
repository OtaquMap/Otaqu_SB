package com.otakumap.domain.auth.dto;

import lombok.Getter;

@Getter
public class KakaoUserInfo {
    private Properties properties;
    private KakaoAccount kakao_account;

    @Getter
    public static class KakaoAccount {
        private String name;
        private String email;
    }

    @Getter
    public static class Properties {
        private String nickname;
    }
}