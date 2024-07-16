package com.think.oms.domain.model.aggregate.shippment;

import com.think.oms.domain.model.constant.OrderSource;
import com.think.oms.domain.model.valueobject.OrderSkuItem;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 三方平台发货回传聚合
 */
@Getter
@Slf4j
public class ShippingAggregate {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 订单来源
     */
    private OrderSource orderSource;

    /**
     * 订单发货详情
     */
    private List<OrderSkuItem> orderSkuItems;

    /**
     * 是否可以回传
     */
    private boolean callback;

    public ShippingAggregate(String orderNo){
        this.orderNo = orderNo;
        this.callback = true;
    }


    public void disabled(){
        this.callback = false;
    }

    /**
     * 过滤非发货数据
     */
    public void filterShipping(){

    }
}
