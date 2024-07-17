package com.think.oms.domain.pl.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OfcOrderQueryResponse {


    private List<OfcOrder> ofcOrders;


    @Data
    @Builder
    public static class OfcOrder{

        private String ofcOrderNo;

        List<OfcOrderShippingItem> shippingItems;
    }

    @Data
    @Builder
    public static class OfcOrderShippingItem{

        private String skuId;

        private Integer shippingAmount;
    }
}
