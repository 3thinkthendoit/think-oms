package com.think.oms.domain.model.aggregate.create;

import lombok.Getter;

@Getter
public class OrderInvoice {

    /**
     * 发票抬头
     */
    private String invoiceName;

    /**
     * 发票明细
     */
    private String invoiceDetails;

    public OrderInvoice(String invoiceName, String invoiceDetails){
        this.invoiceName = invoiceName;
        this.invoiceDetails = invoiceDetails;
    }

}
