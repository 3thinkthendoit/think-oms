package com.think.oms.infrastructure.core.mybatis.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("oms_order_base_info")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderBaseInfo {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "orderNo")
    private String orderNo;


    @TableField(value = "external_order_no")
    private String externalOrderNo;

    @TableField(value = "store_code")
    private String storeCode;

    @TableField(value = "order_title")
    private String orderTitle;

    @TableField(value = "order_price")
    private Long orderPrice;

    @TableField(value = "order_source")
    private String orderSource;

    @TableField(value = "create_time")
    private Date createTime;

    @TableField(value = "update_time")
    private Date updateTime;

}
