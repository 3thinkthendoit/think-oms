package com.think.oms.domain.service;

import com.alibaba.fastjson.JSONObject;
import com.think.oms.domain.model.aggregate.shippingcallback.ShippingCallbackAggregate;
import com.think.oms.domain.pl.request.OfcOrderQueryRequest;
import com.think.oms.domain.pl.request.OrderQueryRequest;
import com.think.oms.domain.pl.request.ShippingCallbackRequest;
import com.think.oms.domain.pl.response.OfcOrderQueryResponse;
import com.think.oms.domain.pl.response.OrderQueryResponse;
import com.think.oms.domain.pl.response.ShippingCallbackResponse;
import com.think.oms.domain.port.gateway.OfcGateway;
import com.think.oms.domain.port.gateway.OrderInfoGateway;
import com.think.oms.domain.port.gateway.ShippingCallbackGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
@Slf4j
public class OrderShippingDomainService {

    @Autowired
    OrderInfoGateway orderInfoGateway;
    @Autowired
    OfcGateway ofcGateway;
    @Autowired
    ShippingCallbackGateway shippingCallbackGateway;

    public void initBaseInfo(ShippingCallbackAggregate aggregate){
        this.initOrderInfo(aggregate);
        this.initSkuShippingInfo(aggregate);
        //完善其他信息
    }

    /**
     * 订单基本信息
     * @param aggregate
     */
    private void initOrderInfo(ShippingCallbackAggregate aggregate){
        OrderQueryRequest request = OrderQueryRequest.builder()
                .orderNo(aggregate.getOrderId().getOrderNo())
                .build();
        OrderQueryResponse response = orderInfoGateway.query(request);
        if(CollectionUtils.isEmpty(response.getOrders())){
            return;
        }
        aggregate.initBaseInfo(response.getOrders().get(0).getSkuInfos());
    }

    /**
     * sku发货信息
     * @param aggregate
     */
    private void initSkuShippingInfo(ShippingCallbackAggregate aggregate){
        OfcOrderQueryRequest request = OfcOrderQueryRequest.builder()
                .orderNo(aggregate.getOrderId().getOrderNo())
                .build();
        OfcOrderQueryResponse response = ofcGateway.query(request);
        if(CollectionUtils.isEmpty(response.getOfcOrders())){
            return;
        }
        response.getOfcOrders().forEach(ofcOrder -> {
            ofcOrder.getShippingItems().forEach(item->{
                aggregate.modifyShippingAmount(item.getSkuId(),item.getShippingAmount());
            });
        });

    }

    /**
     * 通知上游系统发货回传
     * @param aggregate
     */
    public void shippingCallback(ShippingCallbackAggregate aggregate){
        if(!aggregate.isCallback()){
            log.info("orderNo={},无需发货回传!!!",aggregate.getOrderId().getOrderNo());
        }
        ShippingCallbackRequest request = ShippingCallbackRequest.builder()
                .externalOrderNo(aggregate.getOrderId().getExternalOrderNo())
                .orderSource(aggregate.getOrderId().getOrderSource())
                //.shippingInfos();
                .build();
        ShippingCallbackResponse response = shippingCallbackGateway.callback(request);
        Integer status = response.getSuccess()?1:-1;
        aggregate.makeShippingCallbackRecord(status, JSONObject.toJSONString(response));

    }

}
