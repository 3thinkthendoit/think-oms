package com.think.oms.domain.pl.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ShippingCallbackResponse {

    private Boolean success;

    private String msg;
}
