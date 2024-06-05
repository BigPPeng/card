package com.zzuli.agricultural.model.response;

import com.zzuli.agricultural.model.ProductOrder;
import lombok.Builder;
import lombok.Data;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Builder
public class ProductOrderVo {
    private Integer id;

    private Integer productId;
    private String productName;
    private Integer quantity;
    private Double totalPrice;
    private Double price;
    private Integer sellerUserId;
    private String sellerUserName;
    private Integer buyerUserId;
    private String buyerUserName;
    // 1已支付
    private Integer paymentStatus;
    private Integer isValid;
    private String purchaseTimeStr;


    public ProductOrderVo(Integer id, Integer productId, String productName, Integer quantity, Double totalPrice, Double price, Integer sellerUserId, String sellerUserName, Integer buyerUserId, String buyerUserName, Integer paymentStatus, Integer isValid, String purchaseTimeStr) {
        this.id = id;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.price = price;
        this.sellerUserId = sellerUserId;
        this.sellerUserName = sellerUserName;
        this.buyerUserId = buyerUserId;
        this.buyerUserName = buyerUserName;
        this.paymentStatus = paymentStatus;
        this.isValid = isValid;
        this.purchaseTimeStr = purchaseTimeStr;
    }

    public ProductOrderVo(ProductOrder productOrder) {
        this.id = productOrder.getId();
        this.productId = productOrder.getProductId();
        this.quantity = productOrder.getQuantity();
        this.totalPrice = productOrder.getTotalPrice();
        this.price = productOrder.getPrice();
        this.sellerUserId = productOrder.getSellerUserId();
        this.buyerUserId = productOrder.getBuyerUserId();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.purchaseTimeStr = formatter.format(new Date(productOrder.getPurchaseTime() * 1000));
        this.paymentStatus = productOrder.getPaymentStatus();
        this.isValid = productOrder.getIsValid();
    }
}
