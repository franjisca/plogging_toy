package com.myproject.plogging.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FormNotCorrectException extends IllegalArgumentException {
    public FormNotCorrectException(String message) {
        super(message);
    }
}