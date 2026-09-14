package com.venthon.ecommerce.persistence.adapter;

import com.venthon.ecommerce.domain.entity.Order;
import com.venthon.ecommerce.domain.port.output.OrderRepository;
import com.venthon.ecommerce.persistence.repository.OrderJpaRepository;

public class OrderRepositoryAdapter implements OrderRepository {
  private final OrderJpaRepository orderJpaRepository;

    public OrderRepositoryAdapter(OrderJpaRepository orderJpaRepository) {
        this.orderJpaRepository = orderJpaRepository;
    }

    @Override
    public Order saveOrder(Order order) {
        // Map Order to OrderEntity
        // Map OrderEntity to Order
        return null;
    }
}
