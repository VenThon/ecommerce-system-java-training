package com.venthon.ecommerce.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {
        "com.venthon.ecommerce.order.persistence"
})

@EnableJpaRepositories(basePackages = {
        "com.venthon.ecommerce.order.persistence"
})

//@SpringBootApplication
@SpringBootApplication(
        scanBasePackages = "com.venthon.ecommerce"
)
public class OrderServiceApplication {
    static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
