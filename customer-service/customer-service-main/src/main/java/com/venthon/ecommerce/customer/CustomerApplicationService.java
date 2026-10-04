package com.venthon.ecommerce.customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.venthon.ecommerce.customer.persistence"})
@EnableJpaRepositories(basePackages = "com.venthon.ecommerce.customer.persistence")
@SpringBootApplication
public class CustomerApplicationService {
    public static void main(String[] args) {
        SpringApplication.run(CustomerApplicationService.class, args);
    }
}