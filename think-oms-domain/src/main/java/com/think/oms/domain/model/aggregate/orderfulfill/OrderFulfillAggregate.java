package com.think.oms.domain.model.aggregate.orderfulfill;

import lombok.Getter;

import java.util.List;
import java.util.Map;

/**
 * 订单履约聚合
 */
@Getter
public class OrderFulfillAggregate {

    /**
     * 订单号
     */
    private String orderNo;


    /**
     * 店铺编码
     */
    private String storeCode;



    /**
     * 订单下单skuItemMap
     */
    private Map<String,FulfillSkuItem> skuItemMap;


    /**
     * sku 仓库库存信息
     */
    private List<FulfillWarehouse> fulfillWarehouses;

}
