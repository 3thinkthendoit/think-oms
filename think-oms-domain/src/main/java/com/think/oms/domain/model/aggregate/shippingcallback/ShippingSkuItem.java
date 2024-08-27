package com.think.oms.domain.model.aggregate.shippingcallback;

import lombok.Getter;

@Getter
public class ShippingSkuItem {

    /**
     * skuId
     */
    private String skuCode;

    /**
     * 外部skuCode
     */
    private String externalSkuCode;

    /**
     * 快递公司编码
     */
    private String expressCode;

    /**
     * 发货单号
     */
    private String expressNo;

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

    public ShippingSkuItem(String skuCode,String externalSkuCode,Integer skuAmount){
        this.skuCode = skuCode;
        this.externalSkuCode = externalSkuCode;
        this.skuAmount = skuAmount;
        this.shippingAmount = 0;
    }

    public void modify(String expressCode,String expressNo,Integer shippingAmount){
        this.expressCode = expressCode;
        this.expressNo = expressNo;
        this.shippingAmount = shippingAmount;
    }

}
