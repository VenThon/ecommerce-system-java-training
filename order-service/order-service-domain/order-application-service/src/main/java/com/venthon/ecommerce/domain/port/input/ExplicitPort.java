package com.venthon.ecommerce.domain.port.input;

import com.venthon.ecommerce.domain.dto.CreateOrderCommand;

public interface ExplicitPort {
    void execute(CreateOrderCommand createOrderCommand);
}
