package com.zzuli.agricultural.model.response;

import com.zzuli.agricultural.model.Product;
import com.zzuli.agricultural.model.ProductOrder;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

@Builder
@Data
@Slf4j
public class ProductVo {
    private Integer id;
    private String productName;
    private Integer rentSaleType;// 1租，2售卖
    private Integer productStatus;// 0 有效，1无效
    private Integer productType;
    private Double price;
    private Integer quantity;
    private Integer sellerUserId;
    private String startTimeStr;
    private String endTimeStr;


    public ProductVo(Integer id, String productName, Integer rentSaleType, Integer productStatus, Integer productType, Double price, Integer quantity, Integer sellerUserId, String startTimeStr, String endTimeStr) {
        this.id = id;
        this.productName = productName;
        this.rentSaleType = rentSaleType;
        this.productStatus = productStatus;
        this.productType = productType;
        this.price = price;
        this.quantity = quantity;
        this.sellerUserId = sellerUserId;
        this.startTimeStr = startTimeStr;
        this.endTimeStr = endTimeStr;
    }

    public ProductVo(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.rentSaleType = product.getRentSaleType();
        this.productStatus = product.getProductStatus();
        this.productType = product.getProductType();
        this.price = product.getPrice();
        this.quantity = product.getQuantity();
        this.sellerUserId = product.getSellerUserId();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.startTimeStr = formatter.format(new Date(product.getStartTime() * 1000));
        this.endTimeStr = formatter.format(new Date(product.getEndTime() * 1000));
    }
}
