package com.think.oms.ohs.dto.req;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderInfoQueryReq {

    private String orderNo;

    private String externalOrderNo;

    private Integer orderSource;
}
