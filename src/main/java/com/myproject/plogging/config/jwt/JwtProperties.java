package com.myproject.plogging.config.jwt;

public class JwtProperties {
    static final String SECRET = "jiyeon";
    static final int EXPIRATION_TIME = 864000000; // 10일 (1/1000초)
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";
}
