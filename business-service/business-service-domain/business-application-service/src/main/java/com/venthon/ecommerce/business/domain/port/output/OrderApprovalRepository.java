package com.venthon.ecommerce.business.domain.port.output;

import com.venthon.ecommerce.business.domain.entity.OrderApproval;

public interface OrderApprovalRepository {

    OrderApproval save(OrderApproval orderApproval);

}
