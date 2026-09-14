package com.venthon.ecommerce.domain.port.input;

import com.venthon.ecommerce.domain.dto.CreateOrderRequest;

public interface CreateOrderUseCase {
    void execute(CreateOrderRequest createOrderRequest);
}
