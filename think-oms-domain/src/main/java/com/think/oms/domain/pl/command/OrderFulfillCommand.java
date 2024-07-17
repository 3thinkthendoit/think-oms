package com.think.oms.domain.pl.command;

import lombok.Builder;
import lombok.Data;
import org.springframework.util.Assert;

@Data
@Builder
public class OrderFulfillCommand {

    private String orderNo;

    private String ofcOrderNo;

    public void validate(){
        Assert.notNull(orderNo,"orderNo is null !!!");
        Assert.notNull(ofcOrderNo,"ofcOrderNo is null !!!");
        //其他验证自行补充
    }
}
