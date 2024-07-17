package com.think.oms.osh.job;

import com.think.oms.app.service.OrderAppService;
import com.think.oms.domain.pl.command.OrderCreateCommand;
import com.think.oms.infrastructure.acl.api.pdd.PinduoduoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

/**
 * 拼多多定时任务拉单(北向网关)
 */
@Component
public class PinDuoDuoOrderTask {

    @Autowired
    OrderAppService orderAppService;
    @Autowired
    PinduoduoClient pinduoduoClient;

    @Scheduled(fixedRate = 1000L*60*5)
    public void pullOrder() {
        //调用pdd api接口 拉取订单 转成 CreateOrderCommand
        Date end = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(end);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date begin = new Date();
        OrderCreateCommand command = pinduoduoClient.pullOrder(begin,end);
        orderAppService.createOrder(command);
    }
}
