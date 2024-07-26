package com.think.oms.infrastructure.core.rockermq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RocketMqClient  {

    //@Autowired
    private RocketMQTemplate rocketMQTemplate;

    public void send(String topic, String message) {
        rocketMQTemplate.convertAndSend(topic, message);
    }

}
