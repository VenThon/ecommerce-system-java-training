package com.venthon.ecommerce.customer.client.feign;

import com.venthon.ecommerce.customer.client.dto.UserResponse;
import com.venthon.ecommerce.customer.client.fallback.UserFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient(
        name = "user-service",
        path = "/api/users",
        fallback = UserFeignClientFallback.class
)
public interface UserFeignClient {
    @GetMapping("/{id}")
    UserResponse getById(@PathVariable String id);
}