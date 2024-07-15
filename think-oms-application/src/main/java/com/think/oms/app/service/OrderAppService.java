package com.think.oms.app.service;

import com.think.oms.domain.model.aggregate.CreateOrderAggregate;
import com.think.oms.domain.pl.command.CreateOrderCommand;
import com.think.oms.domain.pl.command.SkuShippingCommand;
import com.think.oms.domain.pl.event.OrderCreatedEvent;
import com.think.oms.domain.port.publisher.OrderEventPublisher;
import com.think.oms.domain.port.repository.OrderRepository;
import com.think.oms.domain.service.OrderDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OrderAppService {

    @Autowired
    OrderDomainService orderDomainService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderEventPublisher orderEventPublisher;
    @Autowired


    /**
     * 统一创建订单(流程编排-低耦合)
     * @param command
     */
    public void createOrder(CreateOrderCommand command){
        CreateOrderAggregate aggregate = CreateOrderAggregate.create(command);
        orderDomainService.isExist(aggregate);
        orderDomainService.initBaseInfo(aggregate);
        aggregate.check();
        aggregate.priceCalculate();
        orderRepository.save(aggregate);
        orderEventPublisher.publish(new OrderCreatedEvent(aggregate));
    }

    /**
     * 订单创建后续逻辑
     * @param orderNo
     */
    public void afterOrderBeCreated(String orderNo){
        //通知WMS发货
        //通知结算系统
        //通知发票开票
        //其他业务操作
    }

    public void doHandleOrderFulfillment(SkuShippingCommand command){

    }

}
