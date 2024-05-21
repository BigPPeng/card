package com.zzuli.agricultural.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 农业采购信息对应的售卖信息
 */
@Builder
@Data
@Slf4j
public class AgriculturalSaleInfo {
    private Integer id;
    private Integer saleTime;
    private Integer sellerUserId;
    private String sellerUserName;
    private Integer relatedPurchaseId;
    private Double salePrice;
    private Integer saleQuantity;
    private Double totalSalePrice;
}
