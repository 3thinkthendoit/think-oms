package com.think.oms.domain.port.repository;

import com.think.oms.domain.model.aggregate.create.OrderCreateAggregate;
import com.think.oms.domain.model.aggregate.fulfill.OrderFulfillAggregate;
import com.think.oms.domain.model.dp.OrderId;

public interface OrderRepository {

    public void save(OrderCreateAggregate aggregate);

    public OrderCreateAggregate ofByOrderId(OrderId orderId);

    public void update(OrderFulfillAggregate aggregate);

}
