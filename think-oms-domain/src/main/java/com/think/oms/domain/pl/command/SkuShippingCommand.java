package com.think.oms.domain.pl.command;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SkuShippingCommand {

    private String orderNo;

    private String wmsOrderNo;
}
