package com.venthon.ecommerce.customer.domain.port.output;

import com.example.ecommerce.domain.valueobject.CustomerId;
import com.venthon.ecommerce.customer.domain.entity.Customer;


import java.util.Optional;

public interface CustomerRepository {
    Customer save(Customer customer);
    Optional<Customer> findById(CustomerId customerId);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

}
