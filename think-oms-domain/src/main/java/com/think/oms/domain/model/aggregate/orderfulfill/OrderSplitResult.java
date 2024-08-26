package com.think.oms.domain.model.aggregate.orderfulfill;

import lombok.Getter;

@Getter
public class OrderSplitResult {

    /**
     * 父单号 唯一
     */
    private String parentOrderNo;

    /**
     * 子单号
     */
    private String orderNo;

    /**
     * skuCode
     */
    private String skuCode;

    /**
     * 拆单数量
     */
    private Integer skuAmount;


    /**
     * 分仓编码
     */
    private String warehouseCode;


    public OrderSplitResult(String orderNo,String parentOrderNo,String skuCode,Integer skuAmount,String warehouseCode){
        this.parentOrderNo = parentOrderNo;
        this.orderNo = orderNo;
        this.skuAmount = skuAmount;
        this.skuCode = skuCode;
        this.warehouseCode = warehouseCode;
    }
}
