package com.think.oms.domain.port.gateway;


/**
 * 发票网关
 */
public interface InvoiceGateway {

    public void issue(String orderNo);
}
