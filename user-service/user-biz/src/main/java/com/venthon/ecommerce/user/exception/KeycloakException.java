package com.venthon.ecommerce.user.exception;

import com.venthon.ecommerce.restapi.exception.GlobalException;
import org.springframework.http.HttpStatus;

public class KeycloakException extends GlobalException {
    public KeycloakException(HttpStatus status, String message) {
        super(status, message);
    }
}
