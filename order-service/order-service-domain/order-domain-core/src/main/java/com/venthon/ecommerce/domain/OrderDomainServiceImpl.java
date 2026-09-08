package com.venthon.ecommerce.domain;

import com.venthon.ecommerce.domain.entity.Business;
import com.venthon.ecommerce.domain.entity.Order;
import com.venthon.ecommerce.domain.event.OrderCancelledEvent;
import com.venthon.ecommerce.domain.event.OrderCreatedEvent;
import com.venthon.ecommerce.domain.event.OrderPaidEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class OrderDomainServiceImpl implements OrderDomainService{
    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Business business) {
        // validateBusiness(business);
        //setOrderProductInformation(order, business);
        order.validateOrder();
        order.initializeOrder();
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        return null;
    }

    @Override
    public void approveOrder(Order order) {

    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        return null;
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {

    }
}
