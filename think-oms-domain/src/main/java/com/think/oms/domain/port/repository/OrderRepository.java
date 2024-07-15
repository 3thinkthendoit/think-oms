package com.think.oms.domain.port.repository;

import com.think.oms.domain.model.aggregate.CreateOrderAggregate;
import com.think.oms.domain.model.dp.OrderId;

public interface OrderRepository {

    public void save(CreateOrderAggregate aggregate);

    public CreateOrderAggregate ofByOrderId(OrderId orderId);

    public void update(CreateOrderAggregate aggregate);
}
