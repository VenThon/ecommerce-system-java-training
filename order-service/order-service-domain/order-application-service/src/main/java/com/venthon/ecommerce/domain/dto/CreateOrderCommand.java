package com.venthon.ecommerce.domain.dto;

import com.example.ecommerce.domain.valueobject.BusinessId;
import com.example.ecommerce.domain.valueobject.CustomerId;
import com.example.ecommerce.domain.valueobject.Money;
import com.venthon.ecommerce.domain.valueobject.StreetAddress;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreateOrderCommand(
        UUID customerId,
        UUID businessId,
        BigDecimal price,
        CommandOrderAddress deliveryAddress,
        List<CommandOrderItem> items
) {

}
