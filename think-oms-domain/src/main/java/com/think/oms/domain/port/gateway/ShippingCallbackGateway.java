package com.think.oms.domain.port.gateway;

import com.think.oms.domain.pl.request.ShippingCallbackRequest;
import com.think.oms.domain.pl.response.ShippingCallbackResponse;

public interface ShippingCallbackGateway {

    public ShippingCallbackResponse callback(ShippingCallbackRequest request);
}
