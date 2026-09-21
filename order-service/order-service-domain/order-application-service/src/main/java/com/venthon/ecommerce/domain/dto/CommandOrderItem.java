package com.venthon.ecommerce.domain.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CommandOrderItem(
        UUID productId,
        Integer quantity,
        BigDecimal price,
        BigDecimal subTotal
) {
}
