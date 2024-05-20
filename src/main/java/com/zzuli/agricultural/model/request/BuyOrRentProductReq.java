package com.zzuli.agricultural.model.request;

import lombok.Data;

@Data
public class BuyOrRentProductReq {

    private int userId;
    private int productId;
    private int productCount;
    private int endTime;
}
