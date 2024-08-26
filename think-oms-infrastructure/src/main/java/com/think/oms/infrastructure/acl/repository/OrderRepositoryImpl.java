package com.think.oms.infrastructure.acl.repository;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.think.oms.domain.model.aggregate.create.OrderCreateAggregate;
import com.think.oms.domain.model.aggregate.orderfulfill.OrderFulfillAggregate;
import com.think.oms.domain.model.aggregate.shippingcallback.ShippingCallbackAggregate;
import com.think.oms.domain.model.dp.OrderId;
import com.think.oms.domain.port.repository.OrderRepository;
import com.think.oms.infrastructure.acl.pl.OrderPLUtil;
import com.think.oms.infrastructure.core.mybatis.mapper.OrderInfoMapper;
import com.think.oms.infrastructure.core.mybatis.mapper.OrderSkuInfoMapper;
import com.think.oms.infrastructure.core.mybatis.mapper.OrderSkuItemInfoMapper;
import com.think.oms.infrastructure.core.mybatis.po.OrderBaseInfo;
import com.think.oms.infrastructure.core.mybatis.po.OrderSkuInfo;
import com.think.oms.infrastructure.core.mybatis.po.OrderSkuItemInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import java.util.List;

@Repository
@Slf4j
public class OrderRepositoryImpl implements OrderRepository {

    @Autowired
    OrderInfoMapper orderInfoMapper;
    @Autowired
    OrderSkuInfoMapper orderSkuInfoMapper;
    @Autowired
    OrderSkuItemInfoMapper orderSkuItemInfoMapper;

    /**
     * 持久化订单创建聚合
     * @param aggregate
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(OrderCreateAggregate aggregate){
        OrderBaseInfo orderBaseInfo = OrderPLUtil.plToOrderPo(aggregate);
        List<OrderSkuInfo> orderSkuInfoList = OrderPLUtil.plToOrderSkuPo(aggregate);
        List<OrderSkuItemInfo> orderSkuItemInfoList = OrderPLUtil.plToOrderSkuItemPo(aggregate);
        orderInfoMapper.insert(orderBaseInfo);
        orderSkuInfoMapper.batchInsert(orderSkuInfoList);
        orderSkuItemInfoMapper.batchInsert(orderSkuItemInfoList);
        log.info("save order info success !!! orderInfo={},orderSku={},orderSkuItem={}", JSONObject.toJSONString(orderBaseInfo),
                JSONObject.toJSONString(orderSkuInfoList),JSONObject.toJSONString(orderSkuItemInfoList));
    }

    /**
     * 还原履约订单聚合根
     * @param orderNo
     * @return
     */
    @Override
    public OrderFulfillAggregate ofByOrderId(String orderNo) {
        Assert.notNull(orderNo,"orderNo is null!!!");
        LambdaQueryWrapper<OrderBaseInfo> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(OrderBaseInfo::getOrderNo,orderNo);
        List<OrderBaseInfo> orderList =  orderInfoMapper.selectList(queryWrapper);
        Assert.isTrue(!CollectionUtils.isEmpty(orderList),String.format("根据orderNo=[{}]查询不到订单信息!!!",orderNo));
        //按业务需要查询orderSkuItem
        List<OrderSkuItemInfo> orderSkuItemInfoList = Lists.newArrayList();
        return OrderPLUtil.plToOrderDo(orderList.get(0),orderSkuItemInfoList);
    }

    /**
     * 更新订单履约聚合
     * @param aggregate
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(ShippingCallbackAggregate aggregate) {
        //更新主订单信息

        //更新sku发货信息

        //新增发货回传记录
    }

}
