package com.think.oms.domain.port.gateway;

import com.think.oms.domain.pl.request.SkuInfoQueryRequest;
import com.think.oms.domain.pl.response.SkuInfoQueryResponse;

public interface SkuInfoQueryGateway {

    public SkuInfoQueryResponse query(SkuInfoQueryRequest request);
}
