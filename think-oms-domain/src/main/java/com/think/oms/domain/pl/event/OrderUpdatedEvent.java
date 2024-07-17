package com.think.oms.domain.pl.event;

import com.think.oms.domain.model.aggregate.fulfill.OrderFulfillAggregate;
import lombok.Data;

@Data
public class OrderUpdatedEvent extends OrderOperationEvent{

    public OrderUpdatedEvent(OrderFulfillAggregate aggregate){
        this.setOrderNo(aggregate.getOrderId().getOrderNo());
        this.setPublishType(PublishType.LOCAL);
    }
}
