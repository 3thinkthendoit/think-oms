package com.think.oms.domain.pl;

import lombok.Data;

@Data
public class FulfillOrderInfo {

    /**
     * 发货单号(拆单后父单号)
     */
    private String omsOrderNo;

    /**
     * 原订单号
     */
    private String orderNo;

    /**
     * 仓库编码
     */
    private String warehouseCode;

    @Data
    public static class FulfillSku{

        private String skuCode;

        private Integer skuAmount;
    }
}
