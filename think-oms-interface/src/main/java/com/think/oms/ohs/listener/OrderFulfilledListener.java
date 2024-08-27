package com.think.oms.ohs.listener;

import com.think.oms.app.service.OrderAppService;
import com.think.oms.domain.pl.event.OrderFulfillEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 订单履约事件监听
 */
@Component
@Slf4j
public class OrderFulfilledListener {

    @Autowired
    OrderAppService orderAppService;

    @EventListener(value = OrderFulfillEvent.class)
    @Async
    public void onApplicationEvent(OrderFulfillEvent event) {
        log.info("OrderFulfilledEvent :orderNo=[{}]",event.getOrderNo());
        try {
            orderAppService.fulfillOrder(event.getOrderNo());
        }catch (Exception ex){
            log.error(ex.getMessage(),ex);
        }
    }
}
