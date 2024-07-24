package com.think.oms.domain.model.aggregate.create;

import lombok.Getter;

@Getter
public class ShippingAddress {

    /**
     * 收件人
     */
    private String recipient;

    /**
     * 联系方式
     */
    private String contactInfo;
    /**
     * 地址编码
     */
    private int addressCode;

    /**
     * 地址信息
     */
    private String address;

    public ShippingAddress(int addressCode, String address){
        this.address = address;
        this.addressCode = addressCode;
    }

    public ShippingAddress(String recipient,String contactInfo,String address){
        this.address = address;
        this.contactInfo = contactInfo;
        this.recipient = recipient;
    }
}
