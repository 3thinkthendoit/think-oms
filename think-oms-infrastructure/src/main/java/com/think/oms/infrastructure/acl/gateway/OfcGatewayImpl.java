package com.think.oms.infrastructure.acl.gateway;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.think.oms.domain.pl.request.OfcOrderQueryRequest;
import com.think.oms.domain.pl.response.OfcOrderQueryResponse;
import com.think.oms.domain.port.gateway.OfcGateway;
import com.think.oms.infrastructure.core.rockermq.RocketMqClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import java.util.Map;

/**
 * 订单履约(南向网关)
 */
@Component
@Slf4j
public class OfcGatewayImpl implements OfcGateway {

    @Autowired
    RocketMqClient rocketMqClient;
    //@Autowired
    //OfcIface ofcIface;

    @Override
    public void fulfill(String orderNo) {
        Assert.notNull(orderNo,"orderNo is null !!!");
        Map<String,Object> params = Maps.newHashMap();
        params.put("orderNo",orderNo);
        //调用OFC 微服务iface 或者发送MQ消息 提供北向网关微服务让OFC系统反查
        rocketMqClient.send("think-oms", JSONObject.toJSONString(params));
        log.info("通知订单履约中心发货，orderNo={}",orderNo);
    }

    @Override
    public OfcOrderQueryResponse query(OfcOrderQueryRequest request) {
        //调用OFC 订单微服务
       // ofcIface.query(request.getOfcOrderNo());
        return null;
    }
}
