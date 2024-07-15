package com.think.oms.domain.model.constant;

import lombok.Getter;

@Getter
public enum OrderStatus {

    PAYED(1,"已支付"),CANCLE(2,"取消"),HANG(3,"挂起");

    private int code;
    private String desc;

    private OrderStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
