package com.venthon.ecommerce.customer.restapi.mapper;

import com.venthon.ecommerce.customer.domain.dto.CreateCustomerCommand;
import com.venthon.ecommerce.customer.domain.dto.CreateCustomerResult;
import com.venthon.ecommerce.customer.domain.dto.UpdateCustomerCommand;
import com.venthon.ecommerce.customer.domain.dto.UpdateCustomerResult;
import com.venthon.ecommerce.customer.restapi.dto.CustomerCreateRequest;
import com.venthon.ecommerce.customer.restapi.dto.CustomerCreateResponse;
import com.venthon.ecommerce.customer.restapi.dto.CustomerUpdateRequest;
import com.venthon.ecommerce.customer.restapi.dto.CustomerUpdateResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CustomerWebMapper {
    CreateCustomerCommand customerCreateRequestToCreateCustomerCommand(CustomerCreateRequest customerCreateRequest);
    CustomerCreateResponse createCustomerResultToCustomerCreateResponse(CreateCustomerResult createCustomerResult);

    // customerId comes from the path, the rest from the request body
    @Mapping(source = "customerId", target = "customerId")
    UpdateCustomerCommand customerUpdateRequestToUpdateCustomerCommand(UUID customerId, CustomerUpdateRequest customerUpdateRequest);
    CustomerUpdateResponse updateCustomerResultToCustomerUpdateResponse(UpdateCustomerResult updateCustomerResult);
}