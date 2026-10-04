package com.venthon.ecommerce.business.domain.service;

import com.venthon.ecommerce.business.domain.entity.Business;
import com.venthon.ecommerce.business.domain.event.OrderApprovalEvent;

import java.util.List;

public interface BusinessDomainService {
    OrderApprovalEvent validateOrder(Business business, List<String> failureMessages);
}
