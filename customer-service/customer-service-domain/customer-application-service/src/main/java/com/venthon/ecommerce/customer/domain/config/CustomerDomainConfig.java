package com.venthon.ecommerce.customer.domain.config;

import com.venthon.ecommerce.customer.domain.service.CustomerDomainService;
import com.venthon.ecommerce.customer.domain.service.CustomerDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerDomainConfig {

    @Bean
    public CustomerDomainService customerDomainService() {
        return new CustomerDomainServiceImpl();
    }
}
