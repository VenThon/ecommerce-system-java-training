package com.venthon.ecommerce.order.persistence.repository;

import com.venthon.ecommerce.order.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

//Benefit of using spring Data JPA => Relational DB
//1. Boilerplate code
//2. Abstraction Repository
//3. Derived Query Method (Auto generate SQL)
//4. Object Relational Mapping (ORM) Hibernate
//5. Specification (dynamic query)

public interface OrderJpaRepository extends JpaRepository <OrderEntity, UUID>{
}
