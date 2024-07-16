package com.think.oms.domain.port.gateway;

import com.think.oms.domain.pl.request.OrderQueryRequest;
import com.think.oms.domain.pl.response.OrderQueryResponse;

public interface OrderInfoGateway {

    /**
     * 查询订单
     * @param request
     * @return
     */
    public OrderQueryResponse query(OrderQueryRequest request);
}
