package com.venthon.ecommerce.domain.entity;

import com.example.ecommerce.domain.entity.AggregaeRoot;
import com.example.ecommerce.domain.valueobject.*;
import com.venthon.ecommerce.domain.exception.OrderDomainException;
import com.venthon.ecommerce.domain.valueobject.OrderItemId;
import com.venthon.ecommerce.domain.valueobject.StreetAddress;
import com.venthon.ecommerce.domain.valueobject.TrackingId;

import java.util.List;
import java.util.UUID;

// Mutable attributes អាចផ្លាស់ប្ដូរព័ត៌មានបាន
// Immutable attributes មិនអាចផ្លាស់ប្ដូរព័ត៌មានបានទេ
public class Order extends AggregaeRoot<OrderId> {

    // Can't change after order has been created
    private final CustomerId customerId;

    // Can't change after order has been created
    private final BusinessId businessId;

    // Can't change after order has been created
    private final StreetAddress deliveryAddress;

    // Can't change after order has been created
    private final Money price;

    private final List<OrderItem> items;

    // អាចកែប្រែបានបន្ទាប់ពីបង្កើត order entity រួច
    private TrackingId trackingId;
    private OrderStatus orderStatus;
    private List<String> failureMessages;


    private Order(Builder builder) {
        super.setId(builder.id);
        customerId = builder.customerId;
        businessId = builder.businessId;
        deliveryAddress = builder.deliveryAddress;
        price = builder.price;
        items = builder.items;
        trackingId = builder.trackingId;
        orderStatus = builder.orderStatus;
        failureMessages = builder.failureMessages;
    }

    public static Builder builder() {
        return new Builder();
    }

    public void validateOrder() {
        validateInitialOrder();
        validateTotalPrice();
        validateItemsPrice();
    }

    public void initializeOrder() {
        setId(new OrderId(UUID.randomUUID()));
        trackingId = new TrackingId(UUID.randomUUID());
        orderStatus = OrderStatus.PENDING;
        initializeOrderItems();
    }

    public void pay() {
        if (orderStatus != OrderStatus.PENDING) {
            throw new OrderDomainException("Order is not in correct state for pay operation");
        }
        orderStatus = OrderStatus.PAID;
    }

    public void approve() {
        if (orderStatus != OrderStatus.PAID) {
            throw new OrderDomainException("Order is not in correct state for approve operation");
        }
        orderStatus = OrderStatus.APPROVED;
    }

    public void initCancel(List<String> failureMessages) {
        if (orderStatus != OrderStatus.PAID) {
            throw new OrderDomainException("Order is not in correct state for init cancel operation");
        }
        orderStatus = OrderStatus.CANCELLING;
        updateFailureMessages(failureMessages);
    }

    public void cancel(List<String> failureMessages) {
        if (!(orderStatus == OrderStatus.CANCELLING || orderStatus == OrderStatus.PENDING)) {
            throw new OrderDomainException("Order is not in correct state for cancel operation");
        }
        orderStatus = OrderStatus.CANCELLED;
        updateFailureMessages(failureMessages);
    }

    private void validateInitialOrder() {
        if (orderStatus != null || super.getId() != null) {
            throw new OrderDomainException("Order is not in correct status for initialization");
        }
    }

    private void validateTotalPrice() {
        if (price == null || !price.isGreaterThanZero()) {
            throw new OrderDomainException("Total price must be greater than zero");
        }
    }

    private void validateItemPrice(OrderItem orderItem) {
        if (!orderItem.isPriceValid()) {
            throw new OrderDomainException("Order item price: " + orderItem.getPrice().getAmount() +
                    " is not valid for product: " + orderItem.getProduct().getId().value());
        }
    }

    private void validateItemsPrice() {
        Money orderItemsTotalPrice = items.stream()
                .map(orderItem -> {
                    validateItemPrice(orderItem);
                    return orderItem.getSubTotal();
                })
                .reduce(Money.ZERO, Money::add);

        if (!price.equals(orderItemsTotalPrice)) {
            throw new OrderDomainException("Total price: " + price.getAmount()
                    + " is not equal to order items total price: " + orderItemsTotalPrice.getAmount());
        }
    }

    private void initializeOrderItems() {
        long itemCount = 1;
        for (OrderItem item : items) {
            item.initializeOrderItem(super.getId(), new OrderItemId(itemCount++));
        }
    }

    private void updateFailureMessages(List<String> failureMessages) {
        if (failureMessages != null && this.failureMessages != null) {
            this.failureMessages.addAll(
                    failureMessages.stream().filter(message -> !message.isBlank()).toList()
            );
        }

        if (this.failureMessages == null) {
            this.failureMessages = failureMessages;
        }
    }

    public static final class Builder {
        private OrderId id;
        private CustomerId customerId;
        private BusinessId businessId;
        private StreetAddress deliveryAddress;
        private Money price;
        private List<OrderItem> items;
        private TrackingId trackingId;
        private OrderStatus orderStatus;
        private List<String> failureMessages;

        private Builder() {
        }

        public Builder id(OrderId val) {
            id = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder businessId(BusinessId val) {
            businessId = val;
            return this;
        }

        public Builder deliveryAddress(StreetAddress val) {
            deliveryAddress = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder items(List<OrderItem> val) {
            items = val;
            return this;
        }

        public Builder trackingId(TrackingId val) {
            trackingId = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Builder failureMessages(List<String> val) {
            failureMessages = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}