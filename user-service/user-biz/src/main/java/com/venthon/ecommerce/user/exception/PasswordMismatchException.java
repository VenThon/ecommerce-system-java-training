package com.venthon.ecommerce.user.exception;


import com.venthon.ecommerce.restapi.exception.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;

public class PasswordMismatchException extends GlobalExceptionHandler {
    public PasswordMismatchException(HttpStatus status, String message) {
        super(status, message);
    }
}

