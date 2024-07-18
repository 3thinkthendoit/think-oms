package com.think.oms.domain.model.constant;

import lombok.Getter;

@Getter
public enum OrderType {

    STANDARD(1,"标准订单"),DISCOUNT_FEE(2,"优惠金额");

    private int code;
    private String desc;

    private OrderType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
