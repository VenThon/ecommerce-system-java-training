package com.venthon.ecommerce.domain.service;

import com.venthon.ecommerce.domain.entity.Business;
import com.venthon.ecommerce.domain.entity.Order;
import com.venthon.ecommerce.domain.entity.Product;
import com.venthon.ecommerce.domain.event.OrderCancelledEvent;
import com.venthon.ecommerce.domain.event.OrderCreatedEvent;
import com.venthon.ecommerce.domain.event.OrderPaidEvent;
import com.venthon.ecommerce.domain.exception.OrderDomainException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

public class OrderDomainServiceImpl implements OrderDomainService {
    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Business business) {
        // validateBusiness(business);
        if(!business.isActive()){
            throw new OrderDomainException("Business with ID: " + business.getId() + "is not currently active");
        }

        // Set order product information
        order.getItems().forEach(orderItem -> {
            business.getProducts().forEach(businessProduct -> {
                Product currentProduct = orderItem.getProduct();
                if (businessProduct.equals(currentProduct)) {
                    currentProduct.updateConfirmedNameAndPrice(businessProduct.getName(),
                            businessProduct.getPrice());
                }
            });
        });

        order.validateOrder();
        order.initializeOrder();

        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of("UTC")));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
    }
}
