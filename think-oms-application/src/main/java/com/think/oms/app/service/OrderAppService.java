package com.think.oms.app.service;

import com.think.oms.domain.model.aggregate.createorder.OrderCreateAggregate;
import com.think.oms.domain.model.aggregate.fulfillment.OrderFulfillAggregate;
import com.think.oms.domain.model.aggregate.shippment.ShippingAggregate;
import com.think.oms.domain.pl.command.OrderCreateCommand;
import com.think.oms.domain.pl.command.OrderFulfillmentCommand;
import com.think.oms.domain.pl.event.OrderCreatedEvent;
import com.think.oms.domain.pl.event.OrderUpdatedEvent;
import com.think.oms.domain.port.gateway.InvoiceGateway;
import com.think.oms.domain.port.gateway.OfcGateway;
import com.think.oms.domain.port.publisher.OrderEventPublisher;
import com.think.oms.domain.port.repository.OrderRepository;
import com.think.oms.domain.service.OrderCreateDomainService;
import com.think.oms.domain.service.OrderFulfillDomainService;
import com.think.oms.domain.service.ShippingDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单服务APP层
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
    OfcGateway ofcGateway;
    @Autowired
    InvoiceGateway invoiceGateway;
    @Autowired
    ShippingDomainService shippingDomainService;

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
        orderRepository.save(aggregate);
        orderEventPublisher.publish(new OrderCreatedEvent(aggregate.getOrderId().getOrderNo()));
    }

    /**
     * 订单创建后续逻辑
     * 没有数据变更的可以绕过领域逻辑调用南向网关
     * @param orderNo
     */
    public void doHandleAfterOrderBeCreated(String orderNo){
        //通知订单履约
        ofcGateway.fulfill(orderNo);
        //通知开发票
        invoiceGateway.issue(orderNo);
        //通知olap服务
    }

    /**
     * 订单发货逻辑处理
     * @param command
     */
    public void orderFulfill(OrderFulfillmentCommand command){
        OrderFulfillAggregate aggregate = OrderFulfillAggregate.create(command);
        orderFulfillDomainService.initBaseInfo(aggregate);
        aggregate.check();
        aggregate.fulfill();
        orderRepository.update(aggregate);
        orderEventPublisher.publish(new OrderUpdatedEvent(aggregate));
    }

    /**
     * 三方平台发货回传
     * @param orderNo
     */
    public void shipmentCallBack(String orderNo){
        ShippingAggregate aggregate = new ShippingAggregate(orderNo);
        aggregate.filterShipping();
        shippingDomainService.callback(aggregate);
        orderRepository.update(aggregate);
    }



    public void orderAss(){

    }

}
