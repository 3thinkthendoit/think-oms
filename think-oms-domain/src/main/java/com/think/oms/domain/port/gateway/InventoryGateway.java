package com.think.oms.domain.port.gateway;

import com.think.oms.domain.pl.request.DeductInventoryRequest;
import com.think.oms.domain.pl.response.DeductInventoryResponse;

public interface InventoryGateway {

    public DeductInventoryResponse deduct(DeductInventoryRequest request);
}
