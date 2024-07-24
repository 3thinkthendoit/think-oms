package com.think.oms.domain.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.think.oms.domain.model.aggregate.create.OrderCreateAggregate;
import com.think.oms.domain.pl.SkuFullInfo;
import com.think.oms.domain.pl.request.DeductInventoryRequest;
import com.think.oms.domain.pl.request.OrderQueryRequest;
import com.think.oms.domain.pl.request.SkuInfoQueryRequest;
import com.think.oms.domain.pl.response.DeductInventoryResponse;
import com.think.oms.domain.pl.response.OrderQueryResponse;
import com.think.oms.domain.pl.response.SkuInfoQueryResponse;
import com.think.oms.domain.port.gateway.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import java.util.List;
import java.util.Map;

/**
 * 订单领域服务(与其他域协作逻辑)
 */
@Service
public class OrderCreateDomainService {

    @Autowired
    OrderInfoGateway orderQueryGateway;
    @Autowired
    SkuInfoQueryGateway skuInfoQueryGateway;
    @Autowired
    InventoryGateway inventoryGateway;

    /**
     * 判断订单是存在
     * @param aggregate
     * @return
     */
    public void isExist(OrderCreateAggregate aggregate){
        OrderQueryRequest request = OrderQueryRequest.builder()
                .externalOrderNo(aggregate.getOrderId().getExternalOrderNo())
                .orderSource(aggregate.getOrderId().getOrderSource())
                .build();
        OrderQueryResponse response =  orderQueryGateway.query(request);
        Assert.isTrue(!CollectionUtils.isEmpty(response.getOrders()),String.format("订单externalOrderNo=%s已经存在!!!!",aggregate.getOrderId().getExternalOrderNo()));
    }

    /**
     * 领域方法(高内聚低耦合)
     * @param aggregate
     */
    public void initBaseInfo(OrderCreateAggregate aggregate){
        this.initStoreInfo(aggregate);
        this.initSkuInfo(aggregate);
        this.initInvoiceInfo(aggregate);
        this.initShippingAddress(aggregate);
        this.initBuyer(aggregate);
    }

    /**
     * 完善SKU信息
     * @param aggregate
     */
    private void  initSkuInfo(OrderCreateAggregate aggregate){
        List<String> externalSkuIds = Lists.newArrayList();
        aggregate.getSkuInfos().forEach(orderSku -> {externalSkuIds.add(orderSku.getSkuInfo().getExternalSkuId());});
        //查询商品信息
        SkuInfoQueryRequest request = SkuInfoQueryRequest.builder()
                .externalSkuIds(externalSkuIds)
                .build();
        SkuInfoQueryResponse response = skuInfoQueryGateway.query(request);
        //查询 sku基本信息
        Map<String, SkuFullInfo> skuInfoMap = Maps.newHashMap();
        response.getSkuInfos().forEach(sku->{skuInfoMap.put(sku.getExternalSkuId(),sku);});
        aggregate.modifyOrderSku(skuInfoMap);
    }

    private void  initInvoiceInfo(OrderCreateAggregate aggregate){
        //查询发票域 完善发票信息
        //aggregate.getInvoiceInfo().modify();
    }

    /**
     * 完善地址
     */
    private void initShippingAddress(OrderCreateAggregate aggregate){
        //查询基本信息域 完善下单地址信息
    }

    /**
     * 用户信息
     * @param aggregate
     */
    private void initBuyer(OrderCreateAggregate aggregate){
        //查询用户域 完善用户信息
    }

    /**
     * 店铺信息
     * @param aggregate
     */
    private void initStoreInfo(OrderCreateAggregate aggregate){

    }

    /**
     * 扣减库存
     * 多领域协作
     * @param aggregate
     */
    public void deductInventory(OrderCreateAggregate aggregate){
        DeductInventoryRequest request = DeductInventoryRequest.builder()
                .build();
        DeductInventoryResponse response = inventoryGateway.deduct(request);
        if(response.isDeductSuccess()){
            aggregate.deductInventorySuccess();
        }
    }
}
