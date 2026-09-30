package com.venthon.ecommerce.domain.entity;

import com.example.ecommerce.domain.entity.AggregaeRoot;
import com.example.ecommerce.domain.valueobject.*;


import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

public class Payment extends AggregaeRoot<PaymentId> {
    private final OrderId orderId;
    private final CustomerId customerId;
    private final Money price;

    private PaymentStatus paymentStatus;
    private ZonedDateTime createdAt;

    // Constructor used by Builder
    private Payment(Builder builder) {
        super.setId(builder.id);
        orderId = builder.orderId;
        customerId = builder.customerId;
        price = builder.price;
        paymentStatus = builder.paymentStatus;
        createdAt = builder.createdAt;
    }


    // Validate payment information before processing
    public void validatePayment() {

        // Order must exist
        if (orderId == null) {
            throw new IllegalArgumentException(
                    "Order id cannot be null"
            );
        }

        // Customer must exist
        if (customerId == null) {
            throw new IllegalArgumentException(
                    "Customer id cannot be null"
            );
        }

        // Price must exist
        if (price == null) {
            throw new IllegalArgumentException(
                    "Payment price cannot be null"
            );
        }
    }

    // Initialize a new payment
    public void initializePayment() {

        // Generate new unique Payment ID
        super.setId(
                new PaymentId(UUID.randomUUID())
        );

        // Set payment creation time
        this.createdAt =
                ZonedDateTime.now(ZoneId.of("UTC"));
    }

    // Update the payment status
    public void updateStatus(PaymentStatus paymentStatus) {

        if (paymentStatus == null) {
            throw new IllegalArgumentException(
                    "Payment status cannot be null"
            );
        }

        this.paymentStatus = paymentStatus;
    }

    // Get Order ID
    public OrderId getOrderId() {
        return orderId;
    }


    // Get Customer ID
    public CustomerId getCustomerId() {
        return customerId;
    }


    // Get payment price
    public Money getPrice() {
        return price;
    }

    // Get current payment status
    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }


    // Get payment creation time
    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }


    // Start building Payment object
    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private PaymentId id;
        private OrderId orderId;
        private CustomerId customerId;
        private Money price;
        private PaymentStatus paymentStatus;
        private ZonedDateTime createdAt;

        private Builder() {
        }

//        public static Builder builder() {
//            return new Builder();
//        }

        public Builder id(PaymentId val) {
            id = val;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder paymentStatus(PaymentStatus val) {
            paymentStatus = val;
            return this;
        }

        public Builder createdAt(ZonedDateTime val) {
            createdAt = val;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }
}
