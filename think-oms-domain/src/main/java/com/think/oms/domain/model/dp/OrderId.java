package com.think.oms.domain.model.dp;

import com.think.oms.domain.model.constant.OrderSource;
import lombok.Getter;

@Getter
public class OrderId {

    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 外部订单号
     */
    private  String externalOrderNo;

    /**
     * 订单来源
     */

    private OrderSource orderSource;


    public OrderId(String externalOrderNo,OrderSource orderSource){
        this.externalOrderNo = externalOrderNo;
        this.orderSource = orderSource;
        this.orderNo = System.currentTimeMillis()+"";
    }

    public OrderId(String orderNo){
        this.orderNo = orderNo;
    }

}
