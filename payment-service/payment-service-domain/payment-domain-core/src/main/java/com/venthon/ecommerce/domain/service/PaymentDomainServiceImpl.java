package com.venthon.ecommerce.domain.service;

import com.example.ecommerce.domain.valueobject.PaymentStatus;
import com.venthon.ecommerce.domain.entity.CreditEntry;
import com.venthon.ecommerce.domain.entity.Payment;
import com.venthon.ecommerce.domain.event.PaymentCompletedEvent;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;


// Implementation of PaymentDomainService
public class PaymentDomainServiceImpl implements PaymentDomainService {

    @Override
    public PaymentCompletedEvent validateAndInitiatePayment(
            Payment payment,
            CreditEntry creditEntry
    ) {

        //Validate payment information
        payment.validatePayment();

        //Deduct customer's credit
        creditEntry.subtractCreditAmount(
                payment.getPrice()
        );


        //Initialize payment
        payment.initializePayment();


        //Payment is successfully completed
        payment.updateStatus(
                PaymentStatus.COMPLETED
        );


        //Create and return PaymentCompletedEvent
        // List.of() means there are no failure messages
        return new PaymentCompletedEvent(
                payment,
                ZonedDateTime.now(ZoneId.of("UTC")),
                List.of()
        );
    }
}