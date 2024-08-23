package com.think.oms.domain.model.aggregate.orderfulfill;

import com.google.common.collect.Maps;
import lombok.Getter;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 订单履约聚合
 */
@Getter
public class OrderFulfillAggregate {

    /**
     * 订单号
     */
    private String orderNo;

    /**
     * 店铺编码
     */
    private String storeCode;

    /**
     * 订单下单skuItemMap
     */
    private List<FulfillSkuItem> fulfillSkuItems;


    /**
     * sku 仓库库存信息
     */
    private List<FulfillWarehouse> fulfillWarehouses;

    /**
     * SKU-仓库映射关系
     */
    private Map<String,String> skuMappingWarehouseMap;

    /**
     *店铺-仓库映射关系
     */
    private Map<String,String> storeMappingWarehouseMap;


    /**
     * 拆单拆单结果
     */
    private Map<String, FulfillSkuItem> splitOrders;


    public OrderFulfillAggregate(String orderNo,String storeCode,List<FulfillSkuItem> fulfillSkuItems){
        //初始化校验
        this.fulfillSkuItems = fulfillSkuItems;
        this.orderNo = orderNo;
        this.storeCode = storeCode;
    }

    /**
     * 分仓
     */
    public void dispatch(){
        this.fulfillSkuItems.forEach(this::doDispatch);
    }

    private void doDispatch(FulfillSkuItem skuItem) {
        //1 店铺指定仓库发货
        String warehouseCode = storeMappingWarehouseMap.get(this.storeCode);
        if (!Objects.isNull(warehouseCode)) {
            skuItem.dispatch(warehouseCode,skuItem.getShippingAmount());
            return;
        }
        //2 sku指定了仓库发货
        warehouseCode = skuMappingWarehouseMap.get(skuItem.getSkuCode());
        if (!Objects.isNull(warehouseCode)) {
            skuItem.dispatch(warehouseCode,skuItem.getShippingAmount());
            return;
        }
        //3 地理位置就近原则+库存余量+仓库处理能力 权重计算
        this.dispatchByOptimalWarehouse(skuItem);
    }


    /**
     * 最优仓库选择
     * @param skuItem
     */
    private void dispatchByOptimalWarehouse(FulfillSkuItem skuItem){
        Map<String,Integer> dispatchMap = Maps.newHashMap();
        this.fulfillWarehouses.forEach(warehouse->{
               //dispatchMap
        });
        dispatchMap.forEach((warehouseCode,amount)->{
            skuItem.dispatch(warehouseCode,amount);
        });

    }

    /**
     * 拆单
     */
    public void split(){

    }

}
