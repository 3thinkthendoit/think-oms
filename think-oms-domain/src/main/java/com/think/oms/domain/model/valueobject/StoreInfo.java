package com.think.oms.domain.model.valueobject;

import lombok.Getter;
import org.springframework.util.Assert;

/**
 * 店铺信息
 */
@Getter
public class StoreInfo {

    /**
     * 店铺ID
     */
    private String storeId;

    /**
     * 店铺编码
     */
    private String storeCode;

    /**
     * 店铺名称
     */
    private String storeName;

    public void init(String storeId,String storeCode,String storeName){
        Assert.isNull(storeCode,"storeCode is null!!!");
        Assert.isNull(storeCode,"storeCode is null!!!");
        Assert.isNull(storeCode,"storeCode is null!!!");
        this.storeCode = storeCode;
        this.storeId = storeId;
        this.storeName = storeName;
    }

    public StoreInfo(String storeCode){
        Assert.isNull(storeCode,"storeCode is null!!!");
        this.storeCode = storeCode;
    }
}
