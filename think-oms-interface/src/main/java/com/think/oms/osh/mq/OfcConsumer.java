package com.think.oms.osh.mq;

import com.alibaba.fastjson.JSONObject;
import com.think.oms.app.service.OrderAppService;
import com.think.oms.domain.pl.command.OrderFulfillCommand;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;

/**
 * 监听订单履约系统 发货信息
 */
@Component
@Slf4j
@RocketMQMessageListener(topic = "order-fulfillment-center", consumerGroup = "order-fulfillment-center-group")
public class OfcConsumer implements RocketMQListener<String> {

    @Autowired
    OrderAppService orderAppService;

    @Override
    public void onMessage(String msg) {
       try {
           log.info("收到order-fulfillment-center 发货信息msg={}",msg);
           //解析msg
           JSONObject json = JSONObject.parseObject(msg);
           OrderFulfillCommand command = OrderFulfillCommand.builder()
                   .orderNo(json.getString("orderNo"))
                   .ofcOrderNo(json.getString("ofcOrderNo"))
                   .build();
           orderAppService.orderFulfill(command);
       }catch (Exception ex){
            log.error(ex.getMessage(),ex);
       }
    }
}
