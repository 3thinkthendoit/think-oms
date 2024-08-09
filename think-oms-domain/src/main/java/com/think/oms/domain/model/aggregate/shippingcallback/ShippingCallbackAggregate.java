package com.think.oms.domain.model.aggregate.shippingcallback;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.think.oms.domain.model.constant.OrderSource;
import com.think.oms.domain.model.dp.OrderId;
import com.think.oms.domain.pl.SkuItemInfo;
import com.think.oms.domain.pl.command.OrderFulfillCommand;
import lombok.Getter;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Map;

/**
 * 发货回传聚合
 */
@Getter
public class ShippingCallbackAggregate {

    private OrderId orderId;

    private boolean callback;

    private Map<String,ShippingSkuItem> orderSkuItems;

    private ShippingCallbackRecord shippingCallbackRecord;

    public static ShippingCallbackAggregate create(OrderFulfillCommand command){
        command.validate();
        ShippingCallbackAggregate aggregate = new ShippingCallbackAggregate();
        aggregate.orderId = new OrderId(command.getOrderNo());
        aggregate.orderSkuItems = Maps.newHashMap();
        return aggregate;
    }

    /**
     * 回传校验
     */
    public void check(){
        this.callback = false;
        if(CollectionUtils.isEmpty(orderSkuItems)){
            Assert.isTrue(false,String.format("根据orderNo=[%s]查询不到订单信息!!!",this.orderId.getOrderNo()));
        }
        if(OrderSource.DOU_YIN.getCode() == this.orderId.getOrderSource().getCode()){
            //抖音平台无需发货回传
            return;
        }
        this.orderSkuItems.forEach((k,v)->{
            if(v.getSkuAmount() != 0){
                this.callback = true;
            }
        });
    }

    /**
     * 初始sku信息
     * @param skuItemInfos
     */
    public void initBaseInfo(List<SkuItemInfo> skuItemInfos){
        if(CollectionUtils.isEmpty(skuItemInfos)){
            return;
        }
        skuItemInfos.forEach(skuItemInfo -> {
            //SkuItemInfo 转 ShippingSkuItem
            orderSkuItems.put(skuItemInfo.getSkuFullInfo().getSkuId(),
                    new ShippingSkuItem(skuItemInfo.getSkuFullInfo().getSkuId(),skuItemInfo.getSkuFullInfo().getExternalSkuId(),
                    skuItemInfo.getSkuAmount()));
        });
    }

    /**
     * 更新发货数量
     * @param skuId
     * @param shippingAmount
     */
    public void modifyShippingAmount(String skuId,Integer shippingAmount){
        ShippingSkuItem shippingSkuItem  = orderSkuItems.get(skuId);
        Assert.notNull(shippingSkuItem,String.format("ofc skuId=%s 无法匹配订单skuId!!!",skuId));
        shippingSkuItem.modifyShippingAmount(shippingAmount);
    }

    /**
     * 创建发货回传记录
     */
    public void makeShippingCallbackRecord(Integer status,String callResult){
        this.shippingCallbackRecord = new ShippingCallbackRecord(this.orderId.getOrderNo(),this.orderId.getExternalOrderNo(),
                this.orderId.getOrderSource(), JSONObject.toJSONString(this.orderSkuItems),status,callResult);
    }
}
