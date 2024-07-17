package com.think.oms.domain.model.valueobject.create;


import com.google.common.collect.Maps;
import com.think.oms.domain.model.constant.FeeType;
import lombok.Getter;
import java.util.Map;


public class OrderSkuItem {

    @Getter
    private String skuId;

    @Getter
    private String externalSkuId;

    @Getter
    private String skuCode;

    @Getter
    private String externalSkuCode;

    @Getter
    private Integer skuAmount;

    @Getter
    private Long payPrice;

    private Map<FeeType,Long> feeAmountInfos;

    public OrderSkuItem(String skuId, String skuCode, Integer skuAmount, Long payPrice, Map<FeeType,Long> feeAmountInfos){
        this.skuId = skuId;
        this.skuCode = skuCode;
        this.payPrice = payPrice;
        this.skuAmount = skuAmount;
        this.feeAmountInfos = Maps.newHashMap();
    }
}
