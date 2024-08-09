package com.think.oms.domain.model.aggregate.create;

import com.think.oms.domain.model.constant.Currency;
import com.think.oms.domain.model.constant.FeeType;
import com.think.oms.domain.model.constant.PayType;
import lombok.Getter;

import java.util.Map;

@Getter
public class OrderPay {

    /**
     * 币种
     */
    private Currency currency;

    /**
     * 支付方式
     */
    private PayType payType;

    /**
     * 实际支付金额
     */
    private Long payAmount;

    /**
     * 优惠金额
     */
    private Long discountAmount;


    /**
     * 附加费用/优惠费用信息
     */
    private Map<FeeType,Long > feeAmountMap;


    public  OrderPay(Currency currency,PayType payType,Long payAmount,
                          Long discountAmount,Map<FeeType,Long > feeAmountMap){
            this.currency = currency;
            this.payType = payType;
            this.discountAmount = discountAmount;
            this.payAmount = payAmount;
            this.feeAmountMap = feeAmountMap;
    }

}
