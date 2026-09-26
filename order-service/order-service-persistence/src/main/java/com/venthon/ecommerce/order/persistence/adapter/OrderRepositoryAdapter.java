package com.venthon.ecommerce.order.persistence.adapter;

import com.venthon.ecommerce.domain.entity.Order;
import com.venthon.ecommerce.domain.port.output.OrderRepository;
import com.venthon.ecommerce.order.persistence.entity.OrderEntity;
import com.venthon.ecommerce.order.persistence.mapper.OrderPersistenceMapper;
import com.venthon.ecommerce.order.persistence.repository.OrderJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;
    private final OrderPersistenceMapper orderPersistenceMapper;

    @Override
    public Order saveOrder(Order order) {
        // Map Order to OrderEntity
        OrderEntity orderEntity = orderPersistenceMapper.orderToOrderEntity(order);

        // Set order address
        orderEntity.getOrderAddress().setOrder(orderEntity);

        // Set order items
        orderEntity.getItems().forEach(orderItemEntity -> orderItemEntity.setOrder(orderEntity));

        //  save into database
        OrderEntity saveOrderEntity= orderJpaRepository.save(orderEntity);

        // Map OrderEntity to Order
        return orderPersistenceMapper.orderEntityToOrder(saveOrderEntity);
    }
}
