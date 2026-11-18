package com.venthon.ecommerce.customer.domain.dto;

public record GetCustomersQuery(
        int page,
        int size
) {
}
