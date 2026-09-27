package com.venthon.ecommerce.order.restapi.mapper;

import com.venthon.ecommerce.domain.dto.CreateOrderCommand;
import com.venthon.ecommerce.domain.dto.CreateOrderResult;
import com.venthon.ecommerce.order.restapi.dto.OrderCreateRequest;
import com.venthon.ecommerce.order.restapi.dto.OrderCreateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderWebMapper {


    // Source = OrderCreateRequest
    // Target = CreateOrderCommand

    @Mapping(source = "orderAddress", target = "deliveryAddress")
    CreateOrderCommand orderCreateRequestToCreateOrderCommand(
            OrderCreateRequest orderCreateRequest
    );

    OrderCreateResponse createOrderResultToOrderCreateResponse(
            CreateOrderResult createOrderResult
    );
}
