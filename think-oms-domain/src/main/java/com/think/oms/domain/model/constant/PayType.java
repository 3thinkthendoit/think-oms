package com.think.oms.domain.model.constant;

public enum PayType {

    TRAN_FEE(1,"运费"),DISCOUNT_FEE(2,"优惠金额");

    private int code;
    private String desc;

    private PayType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
