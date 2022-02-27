package com.myblog;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFountResourceException extends RuntimeException{

    public NotFountResourceException() {
    }

    public NotFountResourceException(String message) {
        super(message);
    }

    public NotFountResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}
