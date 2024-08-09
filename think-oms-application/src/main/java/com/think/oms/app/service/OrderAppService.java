package com.think.oms.app.service;

import com.think.oms.domain.model.aggregate.create.OrderCreateAggregate;
import com.think.oms.domain.model.aggregate.shippingcallback.ShippingCallbackAggregate;
import com.think.oms.domain.model.constant.OrderSource;
import com.think.oms.domain.pl.OrderInfo;
import com.think.oms.domain.pl.command.OrderAssCommand;
import com.think.oms.domain.pl.command.OrderCreateCommand;
import com.think.oms.domain.pl.command.OrderFulfillCommand;
import com.think.oms.domain.pl.event.OrderCreatedEvent;
import com.think.oms.domain.pl.event.OrderUpdatedEvent;
import com.think.oms.domain.pl.query.OrderInfoQuery;
import com.think.oms.domain.pl.request.OrderQueryRequest;
import com.think.oms.domain.port.gateway.InvoiceGateway;
import com.think.oms.domain.port.gateway.OfcGateway;
import com.think.oms.domain.port.gateway.OrderInfoGateway;
import com.think.oms.domain.port.publisher.OrderEventPublisher;
import com.think.oms.domain.port.repository.OrderRepository;
import com.think.oms.domain.service.OrderCreateDomainService;
import com.think.oms.domain.service.OrderFulfillDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.List;

/**
 * 订单服务APP层 CQRS 模式
 */
@Slf4j
@Service
public class OrderAppService {

    @Autowired
    OrderCreateDomainService orderCreateDomainService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderEventPublisher orderEventPublisher;
    @Autowired
    OrderFulfillDomainService orderFulfillDomainService;
    @Autowired
    OrderInfoGateway orderInfoGateway;
    @Autowired
    OfcGateway ofcGateway;
    @Autowired
    InvoiceGateway invoiceGateway;

    /**
     * 统一创建订单(流程编排-低耦合)
     * @param command
     */
    public void createOrder(OrderCreateCommand command){
        OrderCreateAggregate aggregate = OrderCreateAggregate.create(command);
        orderCreateDomainService.isExist(aggregate);
        orderCreateDomainService.initBaseInfo(aggregate);
        aggregate.check();
        aggregate.priceCalculate();
        orderCreateDomainService.deductInventory(aggregate);
        orderRepository.save(aggregate);
        orderEventPublisher.publish(new OrderCreatedEvent(aggregate.getOrderId().getOrderNo()));
    }

    /**
     * 订单创建后续逻辑
     * 没有数据变更的可以绕过领域逻辑调用南向网关
     * @param orderNo
     */
    public void doHandleAfterOrderBeCreated(String orderNo){
        OrderQueryRequest request = OrderQueryRequest
                .builder()
                .orderNo(orderNo)
                .build();
        List<OrderInfo> orders = orderInfoGateway.query(request).getOrders();
        if(CollectionUtils.isEmpty(orders)){
            return;
        }
        //通知订单分仓
        ofcGateway.fulfill(orderNo);
        //通知开发票
        invoiceGateway.issue(orderNo);
        //通知olap服务
        //通知结算
    }

    /**
     * 订单发货回传处理
     * @param command
     */
    public void shippingCallback(OrderFulfillCommand command){
        ShippingCallbackAggregate aggregate = ShippingCallbackAggregate.create(command);
        orderFulfillDomainService.initBaseInfo(aggregate);
        aggregate.check();
        orderFulfillDomainService.shippingCallback(aggregate);
        orderRepository.update(aggregate);
        orderEventPublisher.publish(new OrderUpdatedEvent(aggregate));
    }


    /**
     * 订单售后处理
     * @param command
     */
    public void orderAfterSaleService(OrderAssCommand command){

    }

    /**
     * 查询订单信息
     * 查询类query可以直接绕过dmmin层 调用南向网关
     * @param query
     * @return
     */
    public List<OrderInfo> query(OrderInfoQuery query){
        OrderQueryRequest request = OrderQueryRequest
                .builder()
                .orderNo(query.getOrderNo())
                .orderSource(OrderSource.ofByCode(query.getOrderSource()))
                .externalOrderNo(query.getExternalOrderNo())
                .build();
        return orderInfoGateway.query(request).getOrders();
    }

}
