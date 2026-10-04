package com.venthon.ecommerce.business.persistence.adapter;

import com.venthon.ecommerce.business.domain.entity.OrderApproval;
import com.venthon.ecommerce.business.domain.port.output.OrderApprovalRepository;
import com.venthon.ecommerce.business.persistence.entity.OrderApprovalEntity;
import com.venthon.ecommerce.business.persistence.mapper.OrderApprovalPersistenceMapper;
import com.venthon.ecommerce.business.persistence.repository.OrderApprovalJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderApprovalRepositoryAdapter implements OrderApprovalRepository {

    private final OrderApprovalJpaRepository orderApprovalJpaRepository;
    private final OrderApprovalPersistenceMapper orderApprovalPersistenceMapper;

    @Override
    public OrderApproval save(OrderApproval orderApproval) {
        OrderApprovalEntity orderApprovalEntity = orderApprovalPersistenceMapper.orderApprovalToOrderApprovalEntity(orderApproval);

        return orderApprovalPersistenceMapper.orderApprovalEntityToOrderApproval(orderApprovalJpaRepository.save(orderApprovalEntity));
    }
}
