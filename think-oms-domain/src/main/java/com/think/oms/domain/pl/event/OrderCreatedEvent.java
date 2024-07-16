package com.think.oms.domain.pl.event;

import com.think.oms.domain.model.aggregate.createorder.OrderCreateAggregate;
import lombok.Data;

@Data
public class OrderCreatedEvent extends OrderOperationEvent{

    public OrderCreatedEvent(String orderNo){
        this.setOrderNo(orderNo);
        this.setPublishType(PublishType.LOCAL);
    }
}
