package com.venthon.ecommerce.customer.restapi.controller;

import com.venthon.ecommerce.core.PageResponse;
import com.venthon.ecommerce.customer.client.feign.UserFeignClient;
import com.venthon.ecommerce.customer.domain.dto.CustomerResult;
import com.venthon.ecommerce.customer.domain.dto.GetCustomersQuery;
import com.venthon.ecommerce.customer.domain.usecase.GetAllCustomersUseCase;
import com.venthon.ecommerce.customer.restapi.dto.CustomerResponse;
import com.venthon.ecommerce.customer.restapi.mapper.CustomerWebMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerQueryController {

    private final UserFeignClient userFeignClient;
    private final GetAllCustomersUseCase getAllCustomersUseCase;
    private final CustomerWebMapper customerWebMapper;

    @GetMapping
    public PageResponse<CustomerResponse> getAll(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "25") int size
    ) {
        PageResponse<CustomerResult> result = getAllCustomersUseCase.execute(new GetCustomersQuery(page, size));
        return customerWebMapper.toCustomerResponse(result);
    }


    @GetMapping("/check-user/{userId}")
    public UserResponse checkUser(@PathVariable String userId) {
        return userFeignClient.getById(userId);
    }

}
