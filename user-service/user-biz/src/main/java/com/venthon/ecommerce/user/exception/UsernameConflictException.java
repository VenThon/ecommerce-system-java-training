package com.venthon.ecommerce.user.exception;

import com.venthon.ecommerce.restapi.exception.GlobalException;
import com.venthon.ecommerce.restapi.exception.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;

import org.springframework.http.HttpStatus;

public class UsernameConflictException extends GlobalException {
    public UsernameConflictException(HttpStatus status, String message) {
        super(status, message);
    }
}
