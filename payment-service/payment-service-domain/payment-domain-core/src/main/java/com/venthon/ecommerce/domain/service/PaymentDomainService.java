package com.venthon.ecommerce.domain.service;

import com.example.ecommerce.domain.valueobject.PaymentStatus;
import com.venthon.ecommerce.domain.entity.CreditEntry;
import com.venthon.ecommerce.domain.entity.CreditHistory;
import com.venthon.ecommerce.domain.entity.Payment;
import com.venthon.ecommerce.domain.event.PaymentCompletedEvent;

public interface PaymentDomainService {
    CreditHistory validateAndInitiatePayment(Payment payment, CreditEntry creditEntry);

    void updatePaymentStatus(Payment payment, PaymentStatus newPaymentStatus);
}
