package com.zzuli.agricultural.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductOrder {
    private Integer id;

    private Integer productId;
    private Integer quantity;
    private Double totalPrice;
    private Double price;
    private Integer sellerUserId;
    private Integer buyerUserId;
    private Integer purchaseTime;
    // 1已支付
    private Integer paymentStatus;
    private Integer isValid;

}
