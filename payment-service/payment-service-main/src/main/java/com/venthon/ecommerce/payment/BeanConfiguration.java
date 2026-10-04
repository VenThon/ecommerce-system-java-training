package com.venthon.ecommerce.payment;

import com.venthon.ecommerce.domain.service.PaymentDomainService;
import com.venthon.ecommerce.domain.service.PaymentDomainServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public PaymentDomainService paymentDomainService() {
        return new PaymentDomainServiceImpl();
    }
}
