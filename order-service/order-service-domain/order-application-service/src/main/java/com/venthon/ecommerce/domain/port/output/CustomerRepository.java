package com.venthon.ecommerce.domain.port.output;

import com.venthon.ecommerce.domain.entity.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Optional<Customer> findCustomer(UUID customerId);
}
