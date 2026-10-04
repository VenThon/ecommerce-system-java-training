package com.venthon.ecommerce.payment.domain.port.output;

import com.venthon.ecommerce.domain.entity.CreditHistory;

public interface CreditHistoryRepository {
    CreditHistory save(CreditHistory creditHistory);
}
