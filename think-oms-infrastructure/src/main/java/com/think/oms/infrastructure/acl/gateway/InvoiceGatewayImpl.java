package com.think.oms.infrastructure.acl.gateway;

import com.think.oms.domain.port.gateway.InvoiceGateway;
import com.think.oms.infrastructure.core.rockermq.RocketMqClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InvoiceGatewayImpl implements InvoiceGateway {

    @Autowired
    RocketMqClient rocketMqClient;

    @Override
    public void issue(String orderNo) {

    }
}
