package com.think.oms.infrastructure.acl.gateway;

import com.think.oms.domain.pl.request.OrderFulfillRequest;
import com.think.oms.domain.pl.request.ShippingQueryRequest;
import com.think.oms.domain.pl.response.OrderFulfillResponse;
import com.think.oms.domain.pl.response.ShippingQueryResponse;
import com.think.oms.domain.port.gateway.OrderFulfillGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderFulfillGatewayImpl implements OrderFulfillGateway {


    /**
     *
     * @param request
     * @return
     */
    @Override
    public OrderFulfillResponse fulfill(OrderFulfillRequest request) {
       try {
           //调用WMS服务发货

           return OrderFulfillResponse.builder()
                   .isFulfill(true)
                   .build();
       }catch (Exception ex){
            return OrderFulfillResponse.builder()
                    .isFulfill(false)
                    .msg(ex.getMessage())
                    .build();
       }
    }

    @Override
    public ShippingQueryResponse query(ShippingQueryRequest request) {
        //调用WMS查询发货信息
        return null;
    }
}
