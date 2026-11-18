package com.venthon.ecommerce.customer.domain.usecase;

import com.venthon.ecommerce.core.PageResponse;
import com.venthon.ecommerce.customer.domain.dto.CustomerResult;
import com.venthon.ecommerce.customer.domain.dto.GetCustomersQuery;
import com.venthon.ecommerce.customer.domain.port.output.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class GetAllCustomersUseCase {

    private final CustomerRepository customerRepository;

    public PageResponse<CustomerResult> execute(GetCustomersQuery query) {
        log.info("Executing GetAllCustomersUseCase: {}", query);
        return customerRepository.findAll(query.page(), query.size());
    }
}

