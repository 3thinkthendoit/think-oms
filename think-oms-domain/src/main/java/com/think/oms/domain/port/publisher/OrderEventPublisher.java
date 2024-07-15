package com.think.oms.domain.port.publisher;

import com.think.oms.domain.pl.event.OrderOperationEvent;

public interface OrderEventPublisher {

    public void publish(OrderOperationEvent event);
}
