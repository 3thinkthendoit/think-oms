package com.think.oms.infrastructure.acl.gateway;

import com.think.oms.domain.pl.request.RiskCheckRequest;
import com.think.oms.domain.pl.response.RiskCheckResponse;
import com.think.oms.domain.port.gateway.RiskCheckGateway;
import org.springframework.stereotype.Component;

@Component
public class RiskCheckGatewayImpl implements RiskCheckGateway {

    @Override
    public RiskCheckResponse check(RiskCheckRequest request) {
        //调用风控系统检查订单数据
        return null;
    }
}
