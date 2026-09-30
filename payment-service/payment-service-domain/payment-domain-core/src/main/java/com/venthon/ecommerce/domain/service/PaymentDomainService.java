package com.venthon.ecommerce.domain.service;

import com.venthon.ecommerce.domain.entity.CreditEntry;
import com.venthon.ecommerce.domain.entity.Payment;
import com.venthon.ecommerce.domain.event.PaymentCompletedEvent;


//connect the business rules

public interface PaymentDomainService {
    PaymentCompletedEvent validateAndInitiatePayment(
            Payment payment,
            CreditEntry creditEntry
    );
}
