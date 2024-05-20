package com.zzuli.agricultural.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Builder
@Data
@Slf4j
public class Product {
    private Integer id;
    private String productName;
    private Integer rentSaleType;// 1租，2售卖
    private Integer productStatus;// 0 有效，1无效
    private Integer productType;
    private Double price;
    private Integer quantity;
    private Integer sellerUserId;
    private Integer startTime;
    private Integer endTime;
}
