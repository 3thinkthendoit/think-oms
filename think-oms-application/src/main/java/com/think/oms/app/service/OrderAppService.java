package com.think.oms.app.service;

import com.think.oms.domain.model.aggregate.OrderAggregate;
import com.think.oms.domain.pl.command.CreateOrderCommand;
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

    /**
     * 统一创建订单(流程编排-低耦合)
     * @param command
     */
    public void createOrder(CreateOrderCommand command){
        OrderAggregate aggregate = OrderAggregate.create(command);
        orderDomainService.isExist(aggregate);
        orderDomainService.initBaseInfo(aggregate);
        aggregate.check();
        aggregate.priceCalculate();
        orderRepository.save(aggregate);
    }


}
