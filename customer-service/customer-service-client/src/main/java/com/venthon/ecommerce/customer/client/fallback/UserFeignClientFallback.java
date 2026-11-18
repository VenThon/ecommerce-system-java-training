package com.venthon.ecommerce.customer.client.fallback;

import com.venthon.ecommerce.customer.client.dto.UserResponse;
import com.venthon.ecommerce.customer.client.exception.UserServiceUnavailableException;
import com.venthon.ecommerce.customer.client.feign.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserFeignClientFallback implements UserFeignClient {
    @Override
    public UserResponse getById(String id) {
        log.info("UserFeignClientFallback: getById: {}", id);
        // 1. Get cache from Redis
        // 2. Require or verify (payment, user)
        throw new UserServiceUnavailableException("User service is unavailable now, please try again!");
    }

}
