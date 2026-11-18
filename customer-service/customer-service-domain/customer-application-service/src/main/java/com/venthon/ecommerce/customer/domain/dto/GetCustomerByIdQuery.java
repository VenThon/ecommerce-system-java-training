package com.venthon.ecommerce.customer.domain.dto;

import java.util.UUID;

public record GetCustomerByIdQuery(
        UUID customerId
) {
}
