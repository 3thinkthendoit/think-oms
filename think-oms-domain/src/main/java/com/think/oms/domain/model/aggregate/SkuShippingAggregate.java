package com.think.oms.domain.model.aggregate;

import com.think.oms.domain.model.dp.OrderId;
import com.think.oms.domain.model.valueobject.OrderSkuItem;
import com.think.oms.domain.pl.command.SkuShippingCommand;
import lombok.Getter;
import java.util.List;

/**
 * 商品发货聚合
 */
@Getter
public class SkuShippingAggregate {

    private OrderId orderId;

    private String wmsOrderNo;

    private List<OrderSkuItem> orderSkuItems;


    public static SkuShippingAggregate create(SkuShippingCommand command){
        SkuShippingAggregate aggregate = new SkuShippingAggregate();
        aggregate.orderId = new OrderId(command.getOrderNo());
        aggregate.wmsOrderNo = command.getWmsOrderNo();
        return aggregate;
    }

}
