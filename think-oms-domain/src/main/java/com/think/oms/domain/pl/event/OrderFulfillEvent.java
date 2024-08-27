package com.think.oms.domain.pl.event;

import com.think.oms.domain.model.aggregate.shippingcallback.ShippingCallbackAggregate;
import lombok.Data;

@Data
public class OrderFulfillEvent extends OrderOperationEvent{

    public OrderFulfillEvent(String orderNo){
        this.setOrderNo(orderNo);
        this.setPublishType(PublishType.LOCAL);
    }
}
