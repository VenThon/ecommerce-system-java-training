package com.venthon.ecommerce.customer.domain.dto;

public record CreateCustomerCommand(
        String username,
        String familyName,
        String givenName,
        String email,
        String phoneNumber
) {
}
