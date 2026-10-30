package com.venthon.ecommerce.payment;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.venthon.ecommerce.payment.persistence")
@EntityScan(basePackages = "com.venthon.ecommerce.payment.persistence")
@SpringBootApplication(scanBasePackages = "com.venthon.ecommerce.payment.persistence")
public class PaymentApplicationService {
    public static void main(String[] args) {
        SpringApplication.run(PaymentApplicationService.class, args);
    }
}

