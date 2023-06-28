package com.myproject.plogging.exception;


// http Status code
// 2xx -> ok
// 4xx -> 클라이언트 문제
// 5XX -> 서버 문제


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
