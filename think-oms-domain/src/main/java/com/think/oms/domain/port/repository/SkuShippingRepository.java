package com.think.oms.domain.port.repository;

import com.think.oms.domain.model.aggregate.shippingcallback.ShippingCallbackAggregate;

public interface SkuShippingRepository {

    public void save(ShippingCallbackAggregate aggregate);
}
