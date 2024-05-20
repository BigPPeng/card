package com.zzuli.agricultural.model.request;

import lombok.Data;

@Data
public class AddAdministratorsReq {
    private int adminUserid;
    private String userName;
    private String userPass;
    private int userType;
}
