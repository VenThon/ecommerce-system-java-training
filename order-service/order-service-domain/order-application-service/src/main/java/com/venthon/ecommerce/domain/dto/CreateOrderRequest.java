package com.venthon.ecommerce.domain.dto;

import com.example.ecommerce.domain.valueobject.BusinessId;
import com.example.ecommerce.domain.valueobject.CustomerId;
import com.example.ecommerce.domain.valueobject.Money;
import com.venthon.ecommerce.domain.valueobject.StreetAddress;

public record CreateOrderRequest(
        CustomerId customerId,
        BusinessId businessId,
        StreetAddress deliveryAddress,
        Money price
) {

}
