package com.zzuli.agricultural.model.request;

import lombok.Data;

@Data
public class PurchaseReq {
    private int publisherId;
    private String category;
    private double price;
    private int quantity;
}
