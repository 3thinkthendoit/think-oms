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
public class OrderFulfillDomainService {

}
