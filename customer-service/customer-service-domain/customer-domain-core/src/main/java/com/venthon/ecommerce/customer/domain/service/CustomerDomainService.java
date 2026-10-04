package com.venthon.ecommerce.customer.domain.service;


import com.example.ecommerce.domain.valueobject.Email;
import com.example.ecommerce.domain.valueobject.PhoneNumber;
import com.venthon.ecommerce.customer.domain.entity.Customer;
import com.venthon.ecommerce.customer.domain.event.CustomerCreatedEvent;
import com.venthon.ecommerce.customer.domain.event.CustomerDeactivatedEvent;
import com.venthon.ecommerce.customer.domain.event.CustomerUpdatedEvent;

public interface CustomerDomainService {
    CustomerCreatedEvent validateAndInitiateCustomer(Customer customer);

    CustomerUpdatedEvent updateCustomer(Customer customer, String familyName, String givenName,
                                        Email email, PhoneNumber phoneNumber);

    CustomerDeactivatedEvent deactivateCustomer(Customer customer);
}
