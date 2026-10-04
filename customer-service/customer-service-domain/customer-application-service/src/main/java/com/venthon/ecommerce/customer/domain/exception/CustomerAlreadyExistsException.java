package com.venthon.ecommerce.customer.domain.exception;

public class CustomerAlreadyExistsException extends CustomerDomainException {

    public CustomerAlreadyExistsException(String message) {
        super(message);
    }
}
