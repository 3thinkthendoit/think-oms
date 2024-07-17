package com.think.oms.domain.model.valueobject.create;

import com.think.oms.domain.model.constant.SkuType;
import com.think.oms.domain.pl.SkuItemInfo;
import lombok.Getter;
import java.util.List;

@Getter
public class OrderSku {

    /**
     * skuId
     */
    private String skuId;

    /**
     * 外部SKU ID
     */
    private String externalSkuId;

    /**
     * skuCode
     */
    private String skuCode;

    /**
     * 外部skuCode
     */
    private String externalSkuCode;

    /**
     * Sku Name
     */
    private String skuName;

    /**
     * sku价格
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
    private List<OrderSku> subSkuInfos;


    public OrderSku(SkuItemInfo skuInfo, List<OrderSku> subSkuInfos){
        this.externalSkuCode = skuInfo.getExternalSkuCode();
        this.externalSkuId = skuInfo.getExternalSkuId();
        this.skuAmount = skuInfo.getSkuAmount();
        this.skuPayPrice =skuInfo.getSkuPayPrice();
        this.skuType = skuInfo.getSkuType();
        this.subSkuInfos = subSkuInfos;
    }

    /**
     * 领域方法
     * @param skuId
     * @param skuCode
     * @param skuPrice
     */
    public void modifySku(String skuId,String skuCode,Long skuPrice){
        this.skuId = skuId;
        this.skuCode = skuCode;
        this.skuPrice = skuPrice;
    }

}
