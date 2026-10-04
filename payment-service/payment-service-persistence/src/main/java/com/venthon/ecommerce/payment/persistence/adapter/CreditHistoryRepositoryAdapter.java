package com.venthon.ecommerce.payment.persistence.adapter;

import com.venthon.ecommerce.domain.entity.CreditHistory;
import com.venthon.ecommerce.payment.domain.port.output.CreditHistoryRepository;
import com.venthon.ecommerce.payment.persistence.entity.CreditHistoryEntity;
import com.venthon.ecommerce.payment.persistence.mapper.CreditHistoryPersistenceMapper;
import com.venthon.ecommerce.payment.persistence.repository.CreditHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CreditHistoryRepositoryAdapter implements CreditHistoryRepository {
    private final CreditHistoryJpaRepository creditHistoryJpaRepository;
    private final CreditHistoryPersistenceMapper creditHistoryPersistenceMapper;

    @Override
    public CreditHistory save(CreditHistory creditHistory) {
        CreditHistoryEntity creditHistoryEntity =
                creditHistoryPersistenceMapper.creditHistoryToCreditHistoryEntity(creditHistory);
        CreditHistoryEntity savedCreditHistoryEntity = creditHistoryJpaRepository.save(creditHistoryEntity);
        return creditHistoryPersistenceMapper.creditHistoryEntityToCreditHistory(savedCreditHistoryEntity);
    }
}
