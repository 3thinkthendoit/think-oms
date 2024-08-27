package com.think.oms.infrastructure.core.mybatis.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("oms_order_info")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OmsOrderInfo {

    private String omsOrderNo;

    private String orderNo;
}
