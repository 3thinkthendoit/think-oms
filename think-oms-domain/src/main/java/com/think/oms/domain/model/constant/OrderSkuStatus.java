package com.think.oms.domain.model.constant;

import lombok.Getter;

@Getter
public enum OrderSkuStatus {

    NOT_SHIPPED(1,"未发货"),
    PART_SHIPPED(2,"部分发货"),
    SHIPPED(3,"全部发货"),
    RETURN(4,"全部退货"),
    PART_RETURN(5,"部分退货"),
    CHECKED_IN(6,"已签收"),;


    private int code;
    private String desc;

    private OrderSkuStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
