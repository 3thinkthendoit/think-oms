package com.think.oms.osh.listener;

import com.think.oms.app.service.OrderAppService;
import com.think.oms.domain.pl.event.OrderCreatedEvent;
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
public class OrderCreatedListener  {

    @Autowired
    OrderAppService orderAppService;

    @EventListener(value = OrderCreatedEvent.class)
    @Async
    public void onApplicationEvent(OrderCreatedEvent event) {
        log.info("收到OrderCreatedEvent :orderNo=[{}]",event.getOrderNo());
        orderAppService.afterOrderBeCreated(event.getOrderNo());
    }
}
