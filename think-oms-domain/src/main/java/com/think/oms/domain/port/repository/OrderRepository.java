package com.think.oms.domain.port.repository;

import com.think.oms.domain.model.aggregate.OrderAggregate;
import com.think.oms.domain.model.dp.OrderId;

public interface OrderRepository {

    public void save(OrderAggregate aggregate);

    public OrderAggregate ofByOrderId(OrderId orderId);
}
