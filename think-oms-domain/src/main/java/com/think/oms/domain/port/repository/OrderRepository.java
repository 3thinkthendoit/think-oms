package com.think.oms.domain.port.repository;

import com.think.oms.domain.model.aggregate.create.OrderCreateAggregate;
import com.think.oms.domain.model.aggregate.orderfulfill.OrderFulfillAggregate;
import com.think.oms.domain.model.aggregate.shippingcallback.ShippingCallbackAggregate;
import com.think.oms.domain.model.dp.OrderId;

public interface OrderRepository {

    public void save(OrderCreateAggregate aggregate);

    public void update(ShippingCallbackAggregate aggregate);

    public OrderFulfillAggregate ofByOrderId(String orderNo);

}
