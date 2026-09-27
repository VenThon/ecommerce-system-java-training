package com.venthon.ecommerce.domain.port.output;

import com.venthon.ecommerce.domain.entity.Business;

import java.util.Optional;

public interface BusinessRepository {
    Optional<Business> findBusiness(Business business);
}
