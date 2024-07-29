package com.think.oms.ohs.listener;

import com.think.oms.app.service.OrderAppService;
import com.think.oms.domain.pl.event.OrderUpdatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 订单创建事件监听
 */
@Component
@Slf4j
public class OrderUpdatedListener {

    @Autowired
    OrderAppService orderAppService;

    @EventListener(value = OrderUpdatedEvent.class)
    @Async
    public void onApplicationEvent(OrderUpdatedEvent event) {
        log.info("OrderFulfilledEvent :orderNo=[{}]",event.getOrderNo());

    }
}
