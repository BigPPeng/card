package com.zzuli.agricultural.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
@Builder
public class User {
    private Integer id;
    private Integer userType;// com.zzuli.agricultural.model.UserTypeEnum
    private String password;
    private String username;
    private Integer registerTime;
    private Integer userStatus = 1;// 0有效，1无效
    private Integer isActive = 1;// 0有效，1无效
    private String phone;
    private String email;

    public User() {
    }

    public User(Integer id, Integer userType, String password, String username, Integer registerTime, Integer userStatus, Integer isActive, String phone, String email) {
        this.id = id;
        this.userType = userType;
        this.password = password;
        this.username = username;
        this.registerTime = registerTime;
        this.userStatus = userStatus;
        this.isActive = isActive;
        this.phone = phone;
        this.email = email;
    }
}
