package com.think.oms.infrastructure.acl.api.douyin;

import com.think.oms.domain.model.constant.OrderSource;
import com.think.oms.domain.pl.request.ShippingCallbackRequest;
import com.think.oms.domain.pl.response.ShippingCallbackResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 抖音 订单协议
 */
@Component
@Slf4j
public class DouyinClient {

    /**
     * 发货回传
     * @return
     */
    public ShippingCallbackResponse  shippingCallback(ShippingCallbackRequest request){

        Boolean isSuccess = true;
        String msg = "callback success";
        try {

        }catch (Exception ex){
            msg = ex.getMessage();
            isSuccess = false;
        }
        return ShippingCallbackResponse.builder()
                .success(isSuccess)
                .msg(msg)
                .build();
    }


}
