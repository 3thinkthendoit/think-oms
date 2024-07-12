package com.think.oms.domain.model.constant;

import lombok.Getter;

@Getter
public enum SkuType {

    SINGEL(1,"单品"),COMBINE(2,"组合");

    private int code;
    private String desc;

    private SkuType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
