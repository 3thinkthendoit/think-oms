package com.think.oms.domain.model.constant;

import lombok.Getter;

@Getter
public enum OrderSource {
    UN_KNOW(-100,"UN_KNOW"),
    TAO_BAO(1,"淘宝"),
    PDD(2,"拼多多"),
    DOU_YIN(3,"抖音"),
    JD(4,"京东"),
    KUAI_SHOU(5,"快手");

    private int code;
    private String desc;

    private OrderSource(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static OrderSource ofByCode(int code){
        for (int i = 0; i < OrderSource.values().length; i++) {
            if(code == OrderSource.values()[i].code){
                return OrderSource.values()[i];
            }
        }
        return UN_KNOW;
    }
}
