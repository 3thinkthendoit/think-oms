package com.think.oms.infrastructure.core.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.think.oms.infrastructure.core.mybatis.po.OrderSkuInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderSkuInfoMapper extends BaseMapper<OrderSkuInfo> {

    public void batchInsert(@Param("list") List<OrderSkuInfo> list);
}
