package com.think.oms.domain.pl.command;

import com.think.oms.domain.model.constant.OrderSource;
import com.think.oms.domain.model.constant.OrderStatus;
import com.think.oms.domain.model.valueobject.create.OrderUser;
import com.think.oms.domain.pl.SkuItemInfo;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

/**
 * 订单创建command
 */
@Data
@Builder
public class OrderCreateCommand {

    /**
     * 订单号
     */
    private String externalOrderNo;

    /**
     * 订单状态
     */
    private OrderStatus orderStatus;

    /**
     * 订单来源
     */
    private OrderSource orderSource;

    /**
     * 订单标题
     */
    private String orderTitle;

    /**
     * 订单金额 单位分
     */
    private Long orderPrice;

    /**
     * Sku下单信息
     */
    private List<SkuItemInfo> skuInfoList;

    /**
     * 下单用户信息
     */
    private Long userId;

    /**
     * 用户名称
     */
    private String userName;

    /**
     * 用户类型
     */
    private OrderUser.UserType userType;


    /**
     * 收货地址
     */
    private String address;


    /**
     * 发票信息
     */
    private String invoiceName;

    private String invoiceDetails;

    /**
     * 附加信息
     */
    private Map<String,Object> attachInfos;


    /**
     * 校验数据
     */
    public void validate(){
        Assert.notNull(orderSource,"orderSource is null !!!");
        Assert.notNull(orderPrice,"orderPrice is null !!!");
        Assert.notNull(externalOrderNo,"externalOrderNo is null !!!");
        //其他验证自行补充
    }

}
