package com.venthon.ecommerce.domain.dto;

import java.util.UUID;

public record CreateOrderResult(
        UUID orderId
) {
}
