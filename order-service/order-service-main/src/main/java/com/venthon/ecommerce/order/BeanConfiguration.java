package com.venthon.ecommerce.order;


import com.venthon.ecommerce.domain.service.OrderDomainService;
import com.venthon.ecommerce.domain.service.OrderDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//1. add annotation (call annotation base)
//2. java base method like write Java code

@Configuration
public class BeanConfiguration {
    @Bean
    public OrderDomainService orderDomainService(){
        return new OrderDomainServiceImpl();
    }
}
