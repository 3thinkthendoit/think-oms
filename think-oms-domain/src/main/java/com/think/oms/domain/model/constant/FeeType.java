package com.think.oms.domain.model.constant;

import lombok.Getter;

@Getter
public enum FeeType {

    TRAN_FEE(1,"运费"),DISCOUNT_FEE(2,"优惠金额");

    private int code;
    private String desc;

    private FeeType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
