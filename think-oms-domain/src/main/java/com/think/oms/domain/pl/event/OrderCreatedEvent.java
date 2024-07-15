package com.think.oms.domain.pl.event;

import com.think.oms.domain.model.aggregate.CreateOrderAggregate;
import lombok.Data;

@Data
public class OrderCreatedEvent extends OrderOperationEvent{

    public OrderCreatedEvent(CreateOrderAggregate aggregate){
        this.setOrderNo(aggregate.getOrderId().getOrderNo());
        this.setPublishType(PublishType.LOCAL);
    }
}
