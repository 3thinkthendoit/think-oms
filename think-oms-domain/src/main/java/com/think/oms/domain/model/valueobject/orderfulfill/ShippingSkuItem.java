package com.think.oms.domain.model.valueobject.orderfulfill;

import lombok.Getter;

@Getter
public class ShippingSkuItem {

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


}
