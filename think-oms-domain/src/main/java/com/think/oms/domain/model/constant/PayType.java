package com.think.oms.domain.model.constant;

public enum PayType {

    TRAN_FEE(1,"余额支付"),
    ALIPAY(2,"支付宝"),
    WX_PAY(3,"微信支付"),
    UNION_PAY(1,"银联支付"),
    ;

    private int code;
    private String desc;

    private PayType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
