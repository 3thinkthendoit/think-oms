package com.think.oms.domain.model.aggregate.orderfulfill;

import lombok.Getter;
import java.util.Map;

@Getter
public class FulfillWarehouse {

    /**
     * 仓库编码
     */
    private String warehouseCode;

    /**
     * 区域编码
     */
    private String areaCode;

    /**
     * 仓库发货处理水平
     */
    private Integer shippingLevel;

    /**
     * sku的库存余量
     */
    private Map<String,Long> inventoryMap;


    public FulfillWarehouse(String warehouseCode,String areaCode,Map<String,Long> inventoryMap){

    }
}
