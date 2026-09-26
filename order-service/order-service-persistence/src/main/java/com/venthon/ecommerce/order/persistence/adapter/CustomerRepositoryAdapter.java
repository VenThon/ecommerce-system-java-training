package com.venthon.ecommerce.order.persistence.adapter;

import com.venthon.ecommerce.domain.entity.Customer;
import com.venthon.ecommerce.domain.port.output.CustomerRepository;
import com.venthon.ecommerce.order.persistence.mapper.CustomerPersistenceMapper;
import com.venthon.ecommerce.order.persistence.mapper.OrderPersistenceMapper;
import com.venthon.ecommerce.order.persistence.repository.CustomerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {

    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerPersistenceMapper customerPersistenceMapper;

    @Override
    public Optional<Customer> findCustomer(UUID customerId) {
        return customerJpaRepository.findById(customerId)
                .map(customerPersistenceMapper::customerEntityToCustomer);
    }

}
