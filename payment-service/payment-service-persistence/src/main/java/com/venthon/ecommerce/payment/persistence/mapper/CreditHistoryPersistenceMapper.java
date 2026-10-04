package com.venthon.ecommerce.payment.persistence.mapper;

import com.venthon.ecommerce.domain.entity.CreditHistory;
import com.venthon.ecommerce.payment.persistence.entity.CreditHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreditHistoryPersistenceMapper {
    @Mapping(source = "id.value", target = "id")
    @Mapping(source = "customerId.value", target = "customerId")
    @Mapping(source = "amount.amount", target = "amount")
    CreditHistoryEntity creditHistoryToCreditHistoryEntity(CreditHistory creditHistory);

    @Mapping(target = "id.value", source = "id")
    @Mapping(target = "customerId.value", source = "customerId")
    @Mapping(target = "amount.amount", source = "amount")
    CreditHistory creditHistoryEntityToCreditHistory(CreditHistoryEntity creditHistoryEntity);
}
