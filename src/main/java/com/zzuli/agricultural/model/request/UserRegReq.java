package com.zzuli.agricultural.model.request;

import lombok.Data;

@Data
public class UserRegReq {
    private String userName;
    private String userPhone;
    private String userEmail;
    private String userPass;
    private int userType;
}
