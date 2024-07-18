package com.think.oms.infrastructure.acl.gateway;

import com.think.oms.domain.pl.request.DeductInventoryRequest;
import com.think.oms.domain.pl.response.DeductInventoryResponse;
import com.think.oms.domain.port.gateway.InventoryGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InventoryGatewayImpl implements InventoryGateway {


    /**
     *
     * @param request
     * @return
     */
    @Override
    public DeductInventoryResponse deduct(DeductInventoryRequest request) {
        //调用库存服务扣减库存

        return DeductInventoryResponse.builder()
                .build();
    }
}
