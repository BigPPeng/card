package com.zzuli.agricultural.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RentalRecordVo {
    private Integer id;

    private Integer productId;
    private Integer quantity;
    private Double totalPrice;
    private Double price;
    private Integer lessorUserId;// 出租人
    private Integer lesseeUserId;// 承租人
    private Integer rentalStartTime;
    private Integer rentalEndTime;
    private Integer rentalStatus;// 1租赁中
    private Integer isValid;
}
