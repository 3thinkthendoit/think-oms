package com.think.oms.domain.pl.query;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderInfoQuery {

    private String orderNo;

    private String externalOrderNo;

    private Integer orderSource;
}
