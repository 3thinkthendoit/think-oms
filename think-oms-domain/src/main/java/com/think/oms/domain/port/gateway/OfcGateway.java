package com.think.oms.domain.port.gateway;

import com.think.oms.domain.pl.request.OfcOrderQueryRequest;
import com.think.oms.domain.pl.response.OfcOrderQueryResponse;

public interface OfcGateway {

    /**
     * 通知OFC订单履约
     * @param orderNo
     */
    public void fulfill(String orderNo);

    /**
     * 查询OFC订单发货信息
     * @param request
     * @return
     */
    public OfcOrderQueryResponse query(OfcOrderQueryRequest request);

}
