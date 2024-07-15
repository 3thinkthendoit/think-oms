package com.think.oms.infrastructure.acl.pl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.think.oms.domain.model.aggregate.CreateOrderAggregate;
import com.think.oms.domain.model.constant.FeeType;
import com.think.oms.domain.model.valueobject.OrderSkuItem;
import com.think.oms.domain.pl.command.CreateOrderCommand;
import com.think.oms.infrastructure.core.mybatis.po.OrderBaseInfo;
import com.think.oms.infrastructure.core.mybatis.po.OrderSkuInfo;
import com.think.oms.infrastructure.core.mybatis.po.OrderSkuItemInfo;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 南向网关PL转换
 */
public class OrderPLUtil {

    /**
     * 聚合根转PO
     * @param aggregate
     * @return
     */
    public static OrderBaseInfo plToOrderPo(CreateOrderAggregate aggregate){
        OrderBaseInfo order =  OrderBaseInfo.builder()
                .createTime(new Date())
                .updateTime(new Date())
                .externalOrderNo(aggregate.getOrderId().getExternalOrderNo())
                .orderNo(aggregate.getOrderId().getOrderNo())
                .orderPrice(aggregate.getOrderPrice())
                .orderTitle(aggregate.getOrderTitle())
                .build();
        return order;
    }

    public static List<OrderSkuInfo> plToOrderSkuPo(CreateOrderAggregate aggregate){
        List<OrderSkuInfo> list = Lists.newArrayList();
        aggregate.getSkuInfos().forEach(skuInfo -> {
            OrderSkuInfo orderSku = OrderSkuInfo.builder()
                    .build();
            list.add(orderSku);
        });
        return list;
    }

    public static List<OrderSkuItemInfo> plToOrderSkuItemPo(CreateOrderAggregate aggregate){
        List<OrderSkuItemInfo> list = Lists.newArrayList();
        aggregate.getSkuInfos().forEach(skuInfo -> {
            OrderSkuItemInfo orderSkuItem = OrderSkuItemInfo.builder()
                    .build();
            list.add(orderSkuItem);
        });
        return list;
    }

    public static CreateOrderAggregate plToOrderDo(OrderBaseInfo orderBaseInfo, List<OrderSkuInfo> orderSkuInfos, List<OrderSkuItemInfo> skuItemInfos){
        CreateOrderCommand command = CreateOrderCommand.builder()
                .orderPrice(orderBaseInfo.getOrderPrice())
                .orderTitle(orderBaseInfo.getOrderTitle())
                //后续属性 自行还原
                .build();
        List<OrderSkuItem> skuItems = Lists.newArrayList();
        //skuItem po 转 domain
        skuItemInfos.forEach(item->{
            Map<FeeType,Long> feeAmountInfos = Maps.newHashMap();

            skuItems.add(new OrderSkuItem(item.getSkuId(),item.getSkuCode(),item.getSkuAmount(),item.getPayPrice(),feeAmountInfos));
        });
        return CreateOrderAggregate.create(command,skuItems);
    }
}
