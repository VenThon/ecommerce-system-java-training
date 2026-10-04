package com.venthon.ecommerce.customer.domain.event;


import com.example.ecommerce.domain.event.DomainEvent;
import com.example.ecommerce.domain.valueobject.CustomerId;
import com.venthon.ecommerce.customer.domain.entity.Customer;

import java.time.ZonedDateTime;

public class CustomerDeactivatedEvent implements DomainEvent<Customer> {
    private final CustomerId customerId;
    private final ZonedDateTime deactivatedAt;

    public CustomerDeactivatedEvent(CustomerId customerId, ZonedDateTime deactivatedAt){
        this.customerId = customerId;
        this.deactivatedAt = deactivatedAt;
    }

    public CustomerId getCustomerId() {
        return customerId;
    }

    public ZonedDateTime getDeactivatedAt() {
        return deactivatedAt;
    }
}
