package com.think.oms.infrastructure.acl.gateway;

import com.think.oms.domain.model.constant.OrderSource;
import com.think.oms.domain.pl.request.ShippingCallbackRequest;
import com.think.oms.domain.pl.response.ShippingCallbackResponse;
import com.think.oms.domain.port.gateway.ShippingCallbackGateway;
import com.think.oms.infrastructure.acl.api.douyin.DouyinClient;
import com.think.oms.infrastructure.acl.api.pdd.PinduoduoClient;
import com.think.oms.infrastructure.acl.api.taobao.TaoBaoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShippingCallbackGatewayImpl implements ShippingCallbackGateway {

    @Autowired
    TaoBaoClient taoBaoClient;
    @Autowired
    DouyinClient douyinClient;
    @Autowired
    PinduoduoClient pinduoduoClient;
    @Override
    public ShippingCallbackResponse callback(ShippingCallbackRequest request) {
        if(OrderSource.TAO_BAO.getCode() == request.getOrderSource().getCode()){
            return taoBaoClient.shippingCallback(request);
        }else if(OrderSource.DOU_YIN.getCode() == request.getOrderSource().getCode()){
            return douyinClient.shippingCallback(request);
        }else if(OrderSource.PDD.getCode() == request.getOrderSource().getCode()){
            return pinduoduoClient.shippingCallback(request);
        }
        return ShippingCallbackResponse.
                builder().build();
    }
}
