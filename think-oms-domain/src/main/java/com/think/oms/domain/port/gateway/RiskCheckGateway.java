package com.think.oms.domain.port.gateway;

import com.think.oms.domain.pl.request.RiskCheckRequest;
import com.think.oms.domain.pl.response.RiskCheckResponse;

public interface RiskCheckGateway {

    public RiskCheckResponse check(RiskCheckRequest request);
}
