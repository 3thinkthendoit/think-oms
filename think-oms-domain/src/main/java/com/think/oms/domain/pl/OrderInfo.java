package com.think.oms.domain.pl;

import com.think.oms.domain.model.constant.OrderSource;
import com.think.oms.domain.model.constant.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderInfo {

    private String orderNo;

    private String externalOrderNo;

    private OrderSource orderSource;

    private OrderStatus orderStatus;

}
