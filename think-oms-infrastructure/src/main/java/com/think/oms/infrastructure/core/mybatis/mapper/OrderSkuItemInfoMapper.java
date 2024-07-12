package com.think.oms.infrastructure.core.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.think.oms.infrastructure.core.mybatis.po.OrderSkuItemInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderSkuItemInfoMapper extends BaseMapper<OrderSkuItemInfo> {

    public void batchInsert(@Param("list") List<OrderSkuItemInfo> list);
}
