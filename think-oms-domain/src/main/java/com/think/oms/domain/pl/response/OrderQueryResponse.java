package com.think.oms.domain.pl.response;

import com.think.oms.domain.pl.OrderInfo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderQueryResponse {

    private List<OrderInfo> orders;
}
