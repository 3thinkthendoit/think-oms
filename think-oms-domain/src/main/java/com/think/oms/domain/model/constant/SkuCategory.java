package com.think.oms.domain.model.constant;

public enum SkuCategory {

    FRUSH_FOOD(1,"生鲜"),
    GOODS(2,"百货")
    ;

    private int code;
    private String desc;

    private SkuCategory(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
