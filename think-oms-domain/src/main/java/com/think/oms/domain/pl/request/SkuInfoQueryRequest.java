package com.think.oms.domain.pl.request;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SkuInfoQueryRequest {

    private List<String> skuIds;

    private List<String> externalSkuIds;

    private List<String> skuCodes;

    private List<String> externalSkuCodes;
}
