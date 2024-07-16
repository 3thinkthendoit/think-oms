package com.think.oms.domain.port.repository;

import com.think.oms.domain.model.aggregate.createorder.OrderCreateAggregate;
import com.think.oms.domain.model.aggregate.fulfillment.OrderFulfillAggregate;
import com.think.oms.domain.model.aggregate.shippment.ShippingAggregate;
import com.think.oms.domain.model.dp.OrderId;

public interface OrderRepository {

    public void save(OrderCreateAggregate aggregate);

    public OrderCreateAggregate ofByOrderId(OrderId orderId);

    public void update(OrderFulfillAggregate aggregate);

    public void update(ShippingAggregate aggregate);
}
