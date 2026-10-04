package com.venthon.ecommerce.business.domain.port.output;

import com.venthon.ecommerce.business.domain.entity.Business;

import java.util.Optional;

public interface BusinessRepository {

    Optional<Business> findBusinessInformation(Business business);

}
