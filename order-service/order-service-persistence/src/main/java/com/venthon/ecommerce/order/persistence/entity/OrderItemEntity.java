package com.venthon.ecommerce.order.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "order_items")
public class OrderItemEntity {
    @Id
    private Integer id;
    // @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Id
    @ManyToOne
    private OrderEntity order;

    private UUID productId;

    private Integer quantity;
    private BigDecimal price;
    private BigDecimal subTotal;


}
