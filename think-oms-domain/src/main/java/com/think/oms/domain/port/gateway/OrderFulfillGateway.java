package com.think.oms.domain.port.gateway;

import com.think.oms.domain.pl.request.OrderFulfillRequest;
import com.think.oms.domain.pl.request.ShippingQueryRequest;
import com.think.oms.domain.pl.response.OrderFulfillResponse;
import com.think.oms.domain.pl.response.ShippingQueryResponse;

public interface OrderFulfillGateway {

    public OrderFulfillResponse fulfill(OrderFulfillRequest request);

    public ShippingQueryResponse query(ShippingQueryRequest request);
}
