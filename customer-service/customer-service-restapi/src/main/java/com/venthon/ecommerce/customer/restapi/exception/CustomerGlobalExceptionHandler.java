package com.venthon.ecommerce.customer.restapi.exception;

import com.venthon.ecommerce.customer.domain.exception.CustomerAlreadyExistsException;
import com.venthon.ecommerce.customer.domain.exception.CustomerDomainException;
import com.venthon.ecommerce.customer.domain.exception.CustomerNotFoundException;
import com.venthon.ecommerce.restapi.dto.RestApiErrorResponse;
import com.venthon.ecommerce.restapi.exception.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Every error from restapi will be handled by this class
// Validation errors (400) are inherited from GlobalExceptionHandler

@RestControllerAdvice
public class CustomerGlobalExceptionHandler extends GlobalExceptionHandler {

    // Spring picks the most specific handler, so these win over the CustomerDomainException (400) handler below
    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public RestApiErrorResponse<?> handleCustomerNotFoundException(CustomerNotFoundException e) {
        return RestApiErrorResponse.builder()
                .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public RestApiErrorResponse<?> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException e) {
        return RestApiErrorResponse.builder()
                .code(HttpStatus.CONFLICT.getReasonPhrase())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(CustomerDomainException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public RestApiErrorResponse<?> handleCustomerDomainException(CustomerDomainException e) {
        return RestApiErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(e.getMessage())
                .build();
    }
}
