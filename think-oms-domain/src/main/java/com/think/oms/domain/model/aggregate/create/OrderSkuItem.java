package com.think.oms.domain.model.aggregate.create;


import com.google.common.collect.Maps;
import com.think.oms.domain.model.constant.FeeType;
import com.think.oms.domain.model.constant.SkuCategory;
import lombok.Getter;
import java.util.Map;


public class OrderSkuItem {


    @Getter
    private String skuCode;

    @Getter
    private Integer skuAmount;

    @Getter
    private Integer stockAmount;

    @Getter
    private Long payPrice;

    /**
     * 发货优先级
     */
    @Getter
    private Integer priority;

    private Map<FeeType,Long> feeAmountInfos;

    public OrderSkuItem(String skuCode, Integer skuAmount, Long payPrice, Map<FeeType,Long> feeAmountInfos){
        this.skuCode = skuCode;
        this.payPrice = payPrice;
        this.skuAmount = skuAmount;
        this.feeAmountInfos = Maps.newHashMap();
    }

    public void priorityProcessing(Integer priority){
        this.priority = priority;
    }
}
