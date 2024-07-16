package com.think.oms.osh.job;

import com.think.oms.app.service.OrderAppService;
import com.think.oms.domain.pl.command.OrderCreateCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 拼多多定时任务拉单(北向网关)
 */
@Component
public class PinDuoDuoOrderTask {

    @Autowired
    OrderAppService orderAppService;

    @Scheduled(fixedRate = 1000L*60*5)
    public void pullOrder() {
        //调用pdd api接口 拉取订单 转成 CreateOrderCommand
        OrderCreateCommand command = OrderCreateCommand.builder()
                .build();
        orderAppService.createOrder(command);
    }
}
