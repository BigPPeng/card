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
    private String purchaseTimeStr;

    public ProductOrder() {
    }

    public ProductOrder(Integer id, Integer productId, Integer quantity, Double totalPrice, Double price, Integer sellerUserId, Integer buyerUserId, Integer purchaseTime, Integer paymentStatus, Integer isValid) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.price = price;
        this.sellerUserId = sellerUserId;
        this.buyerUserId = buyerUserId;
        this.purchaseTime = purchaseTime;
        this.paymentStatus = paymentStatus;
        this.isValid = isValid;
    }

    public ProductOrder(Integer id, Integer productId, Integer quantity, Double totalPrice, Double price, Integer sellerUserId, Integer buyerUserId, Integer purchaseTime, Integer paymentStatus, Integer isValid, String purchaseTimeStr) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.price = price;
        this.sellerUserId = sellerUserId;
        this.buyerUserId = buyerUserId;
        this.purchaseTime = purchaseTime;
        this.paymentStatus = paymentStatus;
        this.isValid = isValid;
        this.purchaseTimeStr = purchaseTimeStr;
    }
}
