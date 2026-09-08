package com.venthon.ecommerce.domain.event;
import com.example.ecommerce.domain.event.DomainEvent;
import com.venthon.ecommerce.domain.entity.Order;

import java.time.ZonedDateTime;

public abstract class OrderEvent implements DomainEvent<Order> {
    private final Order order;
    private final ZonedDateTime createdAt;

    public OrderEvent(Order order, ZonedDateTime createdAt) {
        this.order = order;
        this.createdAt = createdAt;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public Order getOrder() {
        return order;
    }

}