package com.think.oms.domain.model.constant;

import lombok.Getter;

@Getter
public enum OrderSource {

    TAO_BAO(1,"淘宝"),DOU_YIN(2,"抖音");

    private int code;
    private String desc;

    private OrderSource(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
