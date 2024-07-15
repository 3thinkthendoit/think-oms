package com.think.oms.infrastructure.acl.publisher;

import com.alibaba.fastjson.JSONObject;
import com.think.oms.domain.pl.event.OrderOperationEvent;
import com.think.oms.domain.port.publisher.OrderEventPublisher;
import com.think.oms.infrastructure.core.rockermq.RocketMqClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class OrderEventPublisherImpl implements OrderEventPublisher {

    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    RocketMqClient rocketMqClient;

    /**
     * 发布订单创建事件
     * 采用Spring Event or MQ
     * @param event
     */
    @Override
    public void publish(OrderOperationEvent event) {
        if(OrderOperationEvent.PublishType.LOCAL.equals(event.getPublishType())){
            publisher.publishEvent(event);
        }else {
            rocketMqClient.send("think-oms",JSONObject.toJSONString(event));
        }
    }


}
