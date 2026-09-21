package com.venthon.ecommerce.order.persistence.repository;

import com.venthon.ecommerce.order.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerJpaRepository extends JpaRepository<CustomerEntity, UUID> {
}
