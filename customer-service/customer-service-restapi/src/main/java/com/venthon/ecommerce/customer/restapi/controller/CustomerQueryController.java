package com.venthon.ecommerce.customer.restapi.controller;

import com.venthon.ecommerce.core.PageResponse;
import com.venthon.ecommerce.customer.client.dto.UserResponse;
import com.venthon.ecommerce.customer.client.feign.UserFeignClient;
import com.venthon.ecommerce.customer.domain.dto.CustomerResult;
import com.venthon.ecommerce.customer.domain.dto.GetCustomerByIdQuery;
import com.venthon.ecommerce.customer.domain.dto.GetCustomersQuery;
import com.venthon.ecommerce.customer.domain.usecase.GetAllCustomersUseCase;
import com.venthon.ecommerce.customer.domain.usecase.GetCustomerByIdUseCase;
import com.venthon.ecommerce.customer.restapi.dto.CustomerResponse;
import com.venthon.ecommerce.customer.restapi.mapper.CustomerWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerQueryController {

    private final UserFeignClient userFeignClient;
    private final GetAllCustomersUseCase getAllCustomerUseCase;
    private final CustomerWebMapper customerWebMapper;
    private final GetCustomerByIdUseCase getCustomerByIdUseCase;

    @GetMapping
    public PageResponse<CustomerResponse> getAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int size
    ){
        PageResponse<CustomerResult> result = getAllCustomerUseCase.execute(new GetCustomersQuery(page, size));
        return customerWebMapper.toCustomerResponse(result);
    }

    @GetMapping("/check-user/{userId}")
    public UserResponse checkUser(@PathVariable String userId){
        return userFeignClient.getById(userId);
    }

    @GetMapping("/{customerId}")
    public CustomerResponse getById(@PathVariable UUID customerId) {
        CustomerResult result = getCustomerByIdUseCase.execute(new GetCustomerByIdQuery(customerId));
        return customerWebMapper.toCustomerResponse(result);
    }
}
