package com.venthon.ecommerce.domain.usecase;

import com.venthon.ecommerce.domain.dto.CreateOrderCommand;
import com.venthon.ecommerce.domain.dto.CreateOrderResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class CreateOrderUseCase {

    public CreateOrderResult execute(CreateOrderCommand createOrderCommand) {
        log.info("executing CreateOrderUseCase: {}", createOrderCommand);

        // Validate customer
        // Validate business

        return new CreateOrderResult(UUID.randomUUID());
    }
}

//Insert,Update, Delete => Command => Transaction
//Select => Query => Transaction Read Only
// Create Pattern => Command Query Responsibility Segregation
