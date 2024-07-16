package com.think.oms.domain.model.aggregate.fulfillment;

import com.think.oms.domain.model.dp.OfcId;
import com.think.oms.domain.model.dp.OrderId;
import com.think.oms.domain.model.valueobject.orderfulfill.ShippingSkuItem;
import com.think.oms.domain.pl.command.OrderFulfillmentCommand;
import lombok.Getter;
import java.util.List;

/**
 * 订单履约聚合
 */
@Getter
public class OrderFulfillAggregate {

    private OfcId ofcId;

    private OrderId orderId;

    private List<ShippingSkuItem> orderSkuItems;


    public static OrderFulfillAggregate create(OrderFulfillmentCommand command){
        OrderFulfillAggregate aggregate = new OrderFulfillAggregate();
        aggregate.orderId = new OrderId(command.getOrderNo());
        aggregate.ofcId = new OfcId(command.getOfcOrderNo());
        return aggregate;
    }

    public void check(){

    }

    public void initBaseInfo(){

    }

    /**
     * 处理订单发货
     */
    public void fulfill(){

    }

}
