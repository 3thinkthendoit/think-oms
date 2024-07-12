package com.think.oms.infrastructure.core.mybatis.po;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@TableName("oms_order_sku_item")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderSkuItemInfo {

    private String skuId;

    private String skuCode;

    private Integer skuAmount;

    private Long payPrice;
}
