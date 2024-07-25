package com.think.oms.domain.model.aggregate.create;

import com.think.oms.domain.model.constant.PayType;
import lombok.Getter;

@Getter
public class OrderPay {

    private PayType payType;

    private String payInfo;

}
