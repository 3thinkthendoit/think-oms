package com.think.oms.domain.pl.request;

import com.think.oms.domain.model.constant.OrderSource;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ShippingCallbackRequest {

    private String externalOrderNo;

    private OrderSource orderSource;

    private List<ShippingInfo> shippingInfos;

    @Data
    @Builder
    public static class ShippingInfo{

        private String externalSkuCode;

        private String externalSkuId;

        private Integer skuAmount;

        private Integer ShippingAmount;
    }

}
