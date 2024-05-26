package com.zzuli.agricultural.model.request;

import lombok.Data;

@Data
public class AddProductReq {
    private int productType;
    private double price;
    private int quantity;
    private int sellerUserId;
    private String name;
}
