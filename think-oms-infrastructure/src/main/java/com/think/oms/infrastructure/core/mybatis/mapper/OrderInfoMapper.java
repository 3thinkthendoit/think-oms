package com.think.oms.infrastructure.core.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.think.oms.infrastructure.core.mybatis.po.OrderBaseInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderInfoMapper extends BaseMapper<OrderBaseInfo> {
}
