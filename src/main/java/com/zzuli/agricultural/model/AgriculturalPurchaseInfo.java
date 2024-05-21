package com.zzuli.agricultural.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 农业采购信息
 */
@Builder
@Data
@Slf4j
public class AgriculturalPurchaseInfo {
    private Integer id;
    private String purchaseCategory;
    private Double purchasePrice;
    private Integer purchaseQuantity;
    private Integer purchaserId;
    private String purchaserName;
    private Integer purchaseStartTime;
    private Integer purchaseEndTime;
    private Integer publishTime;
}
