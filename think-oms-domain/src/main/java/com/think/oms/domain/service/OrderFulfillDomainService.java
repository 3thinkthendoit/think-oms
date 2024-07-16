package com.think.oms.domain.service;

import com.think.oms.domain.model.aggregate.fulfillment.OrderFulfillAggregate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderFulfillDomainService {

    public void initBaseInfo(OrderFulfillAggregate aggregate){
        this.initSkuInfo(aggregate);
        this.initSkuShippingInfo(aggregate);
        //完善其他信息
    }

    /**
     * 获取sku基本信息
     * @param aggregate
     */
    private void initSkuInfo(OrderFulfillAggregate aggregate){

    }

    /**
     * 获取sku发货信息
     * @param aggregate
     */
    private void initSkuShippingInfo(OrderFulfillAggregate aggregate){

    }

}
