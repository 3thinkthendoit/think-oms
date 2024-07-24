package com.think.oms.domain.pl.response;

import com.think.oms.domain.pl.InventoryInfo;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DeductInventoryResponse {

   private List<InventoryInfo> inventoryInfos;
}
