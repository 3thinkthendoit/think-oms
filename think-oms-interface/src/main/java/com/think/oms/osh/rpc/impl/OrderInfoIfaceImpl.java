package com.think.oms.osh.rpc.impl;

import com.think.oms.app.service.OrderAppService;
import com.think.oms.domain.pl.OrderInfo;
import com.think.oms.domain.pl.query.OrderInfoQuery;
import com.think.oms.osh.dto.req.OrderInfoQueryReq;
import com.think.oms.osh.dto.resp.OrderInfoQueryResp;
import com.think.oms.osh.rpc.OrderInfoIface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class OrderInfoIfaceImpl implements OrderInfoIface {

    @Autowired
    OrderAppService orderAppService;

    @Override
    public OrderInfoQueryResp query(OrderInfoQueryReq req) {
        //PL dto->bo
        OrderInfoQuery query = OrderInfoQuery.builder()
                .externalOrderNo(req.getExternalOrderNo())
                .orderSource(req.getOrderSource())
                .orderNo(req.getOrderNo())
                .build();
        List<OrderInfo> list = orderAppService.query(query);
        //bo->dto OrderInfo转 OrderInfoQueryResp
        return OrderInfoQueryResp.builder()
//                .skuInfos()
//                .skuItemInfos()
//                .externalOrderNo()
//                .orderNo()
//                .orderSource()
//                .orderStatus()
                .build();
    }
}
