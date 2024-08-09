package com.think.oms.domain.model.constant;

import lombok.Getter;

@Getter
public enum OrderType {

    STANDARD(1,"标准订单"),
    PRE_SALE(2,"预售订单"),
    TEAM_BUY(3,"拼团订单")
    ;

    private int code;
    private String desc;

    private OrderType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
