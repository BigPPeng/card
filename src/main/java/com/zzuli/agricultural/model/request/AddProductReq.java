package com.zzuli.agricultural.model.request;

import lombok.Data;

@Data
public class AddProductReq {
    private final int productType;
    private final double price;
    private final int quantity;
    private final int sellerUserId;
    private final String name;
}
