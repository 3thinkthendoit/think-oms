package com.think.oms.domain.model.constant;

import lombok.Getter;

@Getter
public enum OrderStatus {
    UN_PAY(-1,"未支付"),
    PAYED(1,"已支付"),
    CANCEL(2,"取消"),
    FULFILLED(3,"已发货"),
    HANG_UP(4,"挂起");

    private int code;
    private String desc;

    private OrderStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
