package com.venthon.ecommerce.order.restapi.exception;

import com.venthon.ecommerce.domain.exception.OrderDomainException;
import com.venthon.ecommerce.persistence.business.exception.BusinessPersistenceException;
import com.venthon.ecommerce.restapi.dto.RestApiErrorResponse;
import com.venthon.ecommerce.restapi.exception.GlobalExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


// Extends Form GlobalExceptionHandler error
@RestControllerAdvice
public class OrderGlobalExceptionHandler extends GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OrderDomainException.class)
    public RestApiErrorResponse<?> handleOrderDomainException(OrderDomainException e){
        return RestApiErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(e.getMessage())
                .build();

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessPersistenceException.class)
    public RestApiErrorResponse<?> handleOrderPersistenceException(BusinessPersistenceException e){
        return RestApiErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(e.getMessage())
                .build();

    }
}
