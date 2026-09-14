package com.venthon.ecommerce.persistence.entity;

import com.venthon.ecommerce.domain.entity.Order;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "order_items")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;
    private UUID productId;

    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subTotal;

//    @OneToMany
//    private List<OrderItemEntity> items;

    @OneToOne
    private  ProductEntity product;

    @ManyToOne
     private OrderEntity order;
}
