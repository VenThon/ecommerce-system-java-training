package com.venthon.ecommerce.domain.usecase;

import com.example.ecommerce.domain.valueobject.BusinessId;
import com.example.ecommerce.domain.valueobject.Money;
import com.example.ecommerce.domain.valueobject.ProductId;
import com.venthon.ecommerce.domain.dto.CreateOrderCommand;
import com.venthon.ecommerce.domain.dto.CreateOrderResult;
import com.venthon.ecommerce.domain.entity.Business;
import com.venthon.ecommerce.domain.entity.Order;
import com.venthon.ecommerce.domain.entity.Product;
import com.venthon.ecommerce.domain.event.OrderCreatedEvent;
import com.venthon.ecommerce.domain.exception.OrderDomainException;
import com.venthon.ecommerce.domain.mapper.OrderDomainMapper;
import com.venthon.ecommerce.domain.port.output.BusinessRepository;
import com.venthon.ecommerce.domain.port.output.CustomerRepository;
import com.venthon.ecommerce.domain.port.output.OrderRepository;
import com.venthon.ecommerce.domain.service.OrderDomainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final OrderDomainService orderDomainService;
    private final OrderDomainMapper orderDomainMapper;
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final BusinessRepository businessRepository;

    public CreateOrderResult execute(CreateOrderCommand createOrderCommand){
        log.info("executing CreateOrderUseCase: {}", createOrderCommand);

        // validate customer
        customerRepository.findCustomer(createOrderCommand.customerId())
                .orElseThrow(() -> new OrderDomainException("Could not find customer with ID: " + createOrderCommand.customerId()));

        // validate Business
        List<Product> products = createOrderCommand.items().stream()
                .map(commandOrderItem -> Product.builder()
                        .id(new ProductId(commandOrderItem.productId()))
                        .price(new Money(commandOrderItem.price()))
                        .build())
                .toList();
        Business business = Business.builder()
                .id(new BusinessId(createOrderCommand.businessId()))
                .products(products)
                .build();

        business = businessRepository.findBusiness(business)
                .orElseThrow(() -> new OrderDomainException("Could not fine business with ID: " + createOrderCommand.businessId()));

        log.info("Found business: {}", business);


        // invoke order domain login
        Order order = orderDomainMapper.createOrderCommandToOrder(createOrderCommand);
        //        log.info("Order price: {}", order.getPrice().getAmount());

        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, business);
        log.info("Order created event: {}", orderCreatedEvent.getOrder().getId());

        // save order to database
        Order saveOrder = orderRepository.saveOrder(order);
        if (saveOrder == null){
            throw new OrderDomainException("Cloud not save data into database");
        }
        return new CreateOrderResult(saveOrder.getId().value());
    }
}

//Insert,Update, Delete => Command => Transaction
//Select => Query => Transaction Read Only
// Create Pattern => Command Query Responsibility Segregation
