package com.venthon.ecommerce.customer.domain.port.output;

import com.example.ecommerce.domain.valueobject.CustomerId;
import com.venthon.ecommerce.core.PageResponse;
import com.venthon.ecommerce.customer.domain.dto.CustomerResult;
import com.venthon.ecommerce.customer.domain.entity.Customer;


import java.util.Optional;

public interface CustomerRepository {
    PageResponse<CustomerResult> findAll(int page, int size);

    Customer save(Customer customer);

    Optional<Customer> findById(CustomerId customerId);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
