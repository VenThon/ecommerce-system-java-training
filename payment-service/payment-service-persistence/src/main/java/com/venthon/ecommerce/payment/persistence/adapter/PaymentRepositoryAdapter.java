package com.venthon.ecommerce.payment.persistence.adapter;

import com.venthon.ecommerce.domain.entity.Payment;
import com.venthon.ecommerce.payment.domain.port.output.PaymentRepository;
import com.venthon.ecommerce.payment.persistence.entity.PaymentEntity;
import com.venthon.ecommerce.payment.persistence.mapper.PaymentPersistenceMapper;
import com.venthon.ecommerce.payment.persistence.repository.PaymentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PaymentRepositoryAdapter implements PaymentRepository {
    private final PaymentJpaRepository paymentJpaRepository;
    private final PaymentPersistenceMapper paymentPersistenceMapper;

    @Override
    public Payment savePayment(Payment payment) {
        PaymentEntity paymentEntity = paymentPersistenceMapper.paymentToPaymentEntity(payment);
        PaymentEntity savedPaymentEntity = paymentJpaRepository.save(paymentEntity);
        return paymentPersistenceMapper.paymentEntityToPayment(savedPaymentEntity);
    }
}
