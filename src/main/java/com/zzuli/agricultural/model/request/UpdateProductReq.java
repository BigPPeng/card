package com.zzuli.agricultural.model.request;

import lombok.Data;

@Data
public class UpdateProductReq {
    private double price;
    private int quantity;
    private String name;
    private int productId;
}
