package com.think.oms.domain.pl.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DeductInventoryResponse {

    private boolean deductSuccess;

    private String msg;
}
