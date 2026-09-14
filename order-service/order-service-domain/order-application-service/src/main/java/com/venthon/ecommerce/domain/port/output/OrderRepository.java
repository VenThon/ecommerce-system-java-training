package com.venthon.ecommerce.domain.port.output;

import com.venthon.ecommerce.domain.entity.Order;

public interface OrderRepository {
    Order saveOrder(Order order);
}
