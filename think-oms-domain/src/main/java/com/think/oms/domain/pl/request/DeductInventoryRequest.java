package com.think.oms.domain.pl.request;

import lombok.Builder;
import lombok.Data;
import sun.dc.pr.PRError;

import java.util.List;

@Data
@Builder
public class DeductInventoryRequest {

    private String orderNo;

    private List<DeductInventory> deductInventoryInfos;

    @Data
    @Builder
    public static  class DeductInventory{

        private String skuId;

        private String skuCode;

        private Integer amount;
    }
}
