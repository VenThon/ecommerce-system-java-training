package com.venthon.ecommerce.customer.domain.dto;

import java.util.UUID;

public record DeactivateCustomerCommand(UUID customerId) {
}
