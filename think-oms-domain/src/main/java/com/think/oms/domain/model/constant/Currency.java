package com.think.oms.domain.model.constant;

public enum Currency {
    INSTALL_FEE(1,"CNY"),
    TRAN_FEE(2,"USD");

    private int code;
    private String desc;

    private Currency(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
