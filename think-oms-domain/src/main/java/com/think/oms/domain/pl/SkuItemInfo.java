package com.think.oms.domain.pl;

import com.think.oms.domain.model.constant.SkuType;
import lombok.Data;
import java.util.List;

@Data
public class SkuItemInfo {

    /**
     * skuId
     */

    private String skuId;

    /**
     * 外部skuId
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
     * 下单数量
     */
    private Integer shippingAmount;

    /**
     * 退货数量
     */
    private Integer returnAmount;

    /**
     * 签收数量
     */
    private Integer confirmAmount;

    /**
     * Sku类型
     */
    private SkuType skuType;

    /**
     * 子商品信息
     */
    private List<SkuItemInfo> subSkuList;

}
