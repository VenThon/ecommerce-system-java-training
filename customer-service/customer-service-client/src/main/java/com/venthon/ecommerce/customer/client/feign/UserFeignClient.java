package com.venthon.ecommerce.customer.client.feign;

import com.venthon.ecommerce.customer.client.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(name = "users-service",path = "/api/users")
public interface UserFeignClient {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    UserResponse getById(@PathVariable String id);

}
