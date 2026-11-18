package com.venthon.ecommerce.customer.restapi.mapper;

import com.venthon.ecommerce.core.PageResponse;
import com.venthon.ecommerce.customer.domain.dto.*;
import com.venthon.ecommerce.customer.restapi.dto.*;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CustomerWebMapper {

    CreateCustomerCommand customerCreateRequestToCreateCustomerCommand(
            CustomerCreateRequest request
    );

    CustomerCreateResponse createCustomerResultToCustomerCreateResponse(
            CreateCustomerResult result
    );

    @Mapping(source = "customerId", target = "customerId")
    UpdateCustomerCommand customerUpdateRequestToUpdateCustomerCommand(
            UUID customerId,
            CustomerUpdateRequest request
    );

    CustomerUpdateResponse updateCustomerResultToCustomerUpdateResponse(
            UpdateCustomerResult result
    );

    // Map one customer
    CustomerResponse toCustomerResponse(CustomerResult result);

    // Map paginated customer results
    default PageResponse<CustomerResponse> toCustomerResponse(
            PageResponse<CustomerResult> result) {

        if (result == null) {
            return null;
        }


        throw new UnsupportedOperationException(
                "PageResponse mapping needs its actual field definitions"
        );
    }
}