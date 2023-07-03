package com.myproject.plogging.config.jwt;

public class JwtProperties {
    static final String SECRET = "조익현"; // 우리 서버만 알고 있는 비밀값
    static final int EXPIRATION_TIME = 864000000; // 10일 (1/1000초)
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
}
