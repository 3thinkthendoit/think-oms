package com.think.oms.domain.model.dp;

import lombok.Getter;

@Getter
public class OfcId {

    /**
     * OFC履约单号
     */
    public String ofcOrderNo;

    public OfcId(String ofcOrderNo){
        this.ofcOrderNo = ofcOrderNo;
    }
}
