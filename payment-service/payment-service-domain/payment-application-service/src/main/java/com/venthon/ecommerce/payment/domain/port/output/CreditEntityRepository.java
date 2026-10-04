package com.venthon.ecommerce.payment.domain.port.output;

import com.example.ecommerce.domain.valueobject.CustomerId;
import com.venthon.ecommerce.domain.entity.CreditEntry;

public interface CreditEntityRepository  {
    CreditEntry findByCustomerId(CustomerId customerId);

    CreditEntry save(CreditEntry creditEntry);
}
