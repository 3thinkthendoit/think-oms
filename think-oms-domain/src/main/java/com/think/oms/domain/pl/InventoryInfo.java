package com.think.oms.domain.pl;

import lombok.Data;

@Data
public class InventoryInfo {

    private String skuId;

    private String skuCode;

    private Integer skuAmount;

    private Integer amount;
}
