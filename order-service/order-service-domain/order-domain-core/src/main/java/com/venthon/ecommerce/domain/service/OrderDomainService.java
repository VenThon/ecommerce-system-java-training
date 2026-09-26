package com.venthon.ecommerce.domain.service;

import com.venthon.ecommerce.domain.entity.Business;
import com.venthon.ecommerce.domain.entity.Order;
import com.venthon.ecommerce.domain.event.OrderCancelledEvent;
import com.venthon.ecommerce.domain.event.OrderCreatedEvent;
import com.venthon.ecommerce.domain.event.OrderPaidEvent;

import java.util.List;
public interface OrderDomainService {

    OrderCreatedEvent validateAndInitiateOrder(Order order, Business business);

    OrderPaidEvent payOrder(Order order);

    void approveOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void cancelOrder(Order order, List<String> failureMessages);
}
