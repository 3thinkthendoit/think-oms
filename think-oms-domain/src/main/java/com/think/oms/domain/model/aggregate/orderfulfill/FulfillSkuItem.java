package com.think.oms.domain.model.aggregate.orderfulfill;

import com.think.oms.domain.pl.SkuItemInfo;
import lombok.Getter;

@Getter
public class FulfillSkuItem {

    /**
     * 商品SkuCode
     */
    private String skuCode;

    /**
     * 商品分类
     */
    private String skuType;

    /**
     * 商品名称
     */
    private String skuName;

    /**
     * 下单数量
     */
    private Integer skuAmount;

    /**
     * 发货优先级 0 无需发货 数字越大优先级越高
     */
    private Integer priority;

    /**
     * 预占状态
     */
    private boolean inventoryReservation;

    /**
     * 分仓仓库编码
     */
    private String warehouseCode;


    public FulfillSkuItem(SkuItemInfo skuItemInfo){
        this.skuCode = skuItemInfo.getSkuFullInfo().getSkuCode();
        this.skuName = skuItemInfo.getSkuFullInfo().getSkuName();
        this.skuAmount = skuItemInfo.getSkuAmount();
        this.priority = skuItemInfo.getPriority();
    }

}
