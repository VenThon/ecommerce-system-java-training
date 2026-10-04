package com.venthon.ecommerce.order.restapi.controller;

import com.venthon.ecommerce.domain.dto.CreateOrderCommand;
import com.venthon.ecommerce.domain.dto.CreateOrderResult;
import com.venthon.ecommerce.domain.usecase.CreateOrderUseCase;
import com.venthon.ecommerce.order.restapi.dto.OrderCreateRequest;
import com.venthon.ecommerce.order.restapi.dto.OrderCreateResponse;
import com.venthon.ecommerce.order.restapi.mapper.OrderWebMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor

//Date Friday 19-09-20 learn MapStruct
public class OrderCommandController {

     //Declare required dependency
     private final CreateOrderUseCase createOrderUseCase;
     private final OrderWebMapper orderWebMapper;


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public OrderCreateResponse createOrder(
                 @Valid @RequestBody OrderCreateRequest orderCreateRequest
         ){

          // Mapping logic
          CreateOrderCommand createOrderCommand = orderWebMapper
                  .orderCreateRequestToCreateOrderCommand(orderCreateRequest);

          // UseCase logic
          CreateOrderResult createOrderResult = createOrderUseCase.execute(createOrderCommand);

          // Mapping logic
          return orderWebMapper.createOrderResultToOrderCreateResponse(createOrderResult);
     }
}
