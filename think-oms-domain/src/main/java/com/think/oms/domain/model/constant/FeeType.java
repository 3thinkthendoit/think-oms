package com.think.oms.domain.model.constant;

import lombok.Getter;

@Getter
public enum FeeType {
    INSTALL_FEE(0,"安装费"),
    TRAN_FEE(1,"运费"),
    DISCOUNT_FEE(2,"优惠金额"),
    COMMISSION(3,"提成");


    private int code;
    private String desc;

    private FeeType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
