package com.think.oms.domain.pl;

import com.think.oms.domain.model.constant.SkuType;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.Assert;

import java.util.List;

@Data
@Builder
public class SkuFullInfo {

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
     * 商品类型
     */
    private SkuType skuType;

    /**
     * 商品数量
     */
    private Integer skuAmount;

    /**
     * 子商品信息
     */
    private List<SkuFullInfo> subSkuInfos;


    //其他商品信息 需要自己补充

    public void validate(){
        //sku 属性判断
        Assert.isNull(skuId,"skuId is null!!!!");
        Assert.isNull(externalSkuId,"externalSkuId is null!!!!");
        Assert.isNull(skuCode,"skuCode is null!!!!");
        Assert.isNull(externalSkuCode,"externalSkuCode is null!!!!");
        Assert.isNull(skuName,"skuName is null!!!!");
        Assert.isNull(skuPrice,"skuPrice is null!!!!");
        Assert.isNull(skuType,"skuType is null!!!!");
        Assert.isNull(skuAmount,"skuAmount is null!!!!");
    }
}
