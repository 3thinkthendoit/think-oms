package com.think.oms.domain.model.aggregate.fulfill;

import com.think.oms.domain.model.constant.OrderSource;
import lombok.Getter;

/**
 * 发货回传记录
 */
@Getter
public class ShippingCallbackRecord {

    private String orderNo;

    private String externalOrderNo;

    private OrderSource orderSource;

    private Integer callStatus;

    private String result;

    /**
     * 发货回传 JSON格式 {skuId= amount=}
     */
    private String shippingInfo;

    public ShippingCallbackRecord(String orderNo,String externalOrderNo,OrderSource orderSource,String shippingInfo,
                                  Integer callStatus, String result){
        this.externalOrderNo = externalOrderNo;
        this.orderSource = orderSource;
        this.shippingInfo = shippingInfo;
        this.orderNo = orderNo;
    }
}
