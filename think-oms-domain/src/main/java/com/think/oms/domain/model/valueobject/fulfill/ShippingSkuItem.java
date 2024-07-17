package com.think.oms.domain.model.valueobject.fulfill;

import lombok.Getter;

@Getter
public class ShippingSkuItem {

    /**
     * skuId
     */
    private String skuId;

    /**
     * 外部skuId
     */
    private String externalSkuId;

    /**
     * skuCode
     */
    private String skuCode;

    /**
     * 外部skuCode
     */
    private String externalSkuCode;

    /**
     * sku数量
     */
    private Integer skuAmount;


    /**
     * 发货数量
     */
    private Integer shippingAmount;


    /**
     * 累加数量
     * @param shippingAmount
     */
    public void modifyShippingAmount(Integer shippingAmount){
        this.shippingAmount += shippingAmount;
    }

    public ShippingSkuItem(String skuId,String externalSkuId,Integer skuAmount){
        this.skuId = skuId;
        this.externalSkuId = externalSkuId;
        this.skuAmount = skuAmount;
        this.shippingAmount = 0;
    }

}
