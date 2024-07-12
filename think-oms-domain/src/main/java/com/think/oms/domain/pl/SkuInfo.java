package com.think.oms.domain.pl;

import com.think.oms.domain.model.constant.SkuType;
import lombok.Data;

import java.util.List;

@Data
public class SkuInfo {

    /**
     * skuId
     */

    private String skuId;


    private String externalSkuId;

    /**
     * skuCode
     */
    private String skuCode;


    private String externalSkuCode;

    /**
     * Sku Name
     */
    private String skuName;

    /**
     * Sku原始价格
     */
    private Long skuPrice;

    /**
     * sku实付价格
     */
    private Long skuPayPrice;

    /**
     * 下单数量
     */
    private Integer skuAmount;

    /**
     * Sku类型
     */
    private SkuType skuType;

    /**
     * 子商品信息
     */
    private List<SkuInfo> subSkuList;
}
