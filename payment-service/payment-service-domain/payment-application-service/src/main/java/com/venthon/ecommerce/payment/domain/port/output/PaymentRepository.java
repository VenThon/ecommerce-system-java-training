package com.venthon.ecommerce.payment.domain.port.output;

import com.venthon.ecommerce.domain.entity.Payment;

public interface PaymentRepository {
    Payment savePayment(Payment payment);
}
