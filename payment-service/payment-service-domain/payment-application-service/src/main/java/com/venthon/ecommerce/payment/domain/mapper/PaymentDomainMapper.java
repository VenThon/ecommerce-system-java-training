package com.venthon.ecommerce.payment.domain.mapper;

import com.venthon.ecommerce.domain.entity.Payment;
import com.venthon.ecommerce.payment.domain.dto.CreatePaymentCommand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentDomainMapper {
    @Mapping(source = "orderId", target = "orderId.value")
    @Mapping(source = "customerId", target = "customerId.value")
    @Mapping(source = "price", target = "price.amount")
    Payment createPaymentCommandToPayment(CreatePaymentCommand createPaymentCommand);
}
