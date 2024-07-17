package com.think.oms.infrastructure.acl.api.pdd;

import com.think.oms.domain.pl.command.OrderCreateCommand;
import com.think.oms.domain.pl.request.ShippingCallbackRequest;
import com.think.oms.domain.pl.response.ShippingCallbackResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * PDD 订单协议
 */
@Component
@Slf4j
public class PinduoduoClient {

    public ShippingCallbackResponse  shippingCallback(ShippingCallbackRequest request){

        return null;
    }

    /**
     * 按更新时间拉取订单信息
     * @param begin
     * @param end
     * @return
     */
    public OrderCreateCommand pullOrder(Date begin, Date end){

        return OrderCreateCommand.builder()
                .build();
    }
}
