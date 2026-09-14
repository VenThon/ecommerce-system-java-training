package com.venthon.ecommerce.domain.dto;

import com.example.ecommerce.domain.valueobject.OrderId;

public record CreateOrderResponse(
 OrderId orderId
) {
}
