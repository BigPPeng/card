package com.card.model;

import java.util.Date;

/**
 * 用户基本信息表
 * Created by hongpeng.cui on 2019/3/25.
 */
public class User {
    /*
     CREATE TABLE `card_user` (
     `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
     `name` varchar(30) NOT NULL DEFAULT '' COMMENT '用户昵称',
     `real_name` varchar(30) NOT NULL DEFAULT '' COMMENT '用户实名',
     `phone_number` varchar(30) NOT NULL DEFAULT '' COMMENT '用户手机号',
     `identity_card_number` varchar(40) NOT NULL DEFAULT '' COMMENT '身份证ID',
     `user_email` varchar(40) NOT NULL DEFAULT '' COMMENT '用户email',
     `home_address` varchar(200) NOT NULL DEFAULT '' COMMENT '住址',
     `receive_address` varchar(200) NOT NULL DEFAULT '' COMMENT '住址',
     `status` int(11) NOT NULL DEFAULT '0' COMMENT '用户状态',
     `login_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '上次登陆时间',
     `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
     `extend_one` varchar(30) DEFAULT NULL COMMENT '扩展字段1',
     `_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后操作时间戳',
     PRIMARY KEY (`id`),
     UNIQUE KEY `name` (`name`) USING BTREE
     )
     */
    private long id;
    private String name;
    private String realName;
    private String phoneNumber;
    private String identityCardNumber;
    private String userEmail;
    private String homeAddress;
    private String receiveAddress;
    private int status;
    private Date loginTime;
    private Date createTime;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIdentityCardNumber() {
        return identityCardNumber;
    }

    public void setIdentityCardNumber(String identityCardNumber) {
        this.identityCardNumber = identityCardNumber;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getReceiveAddress() {
        return receiveAddress;
    }

    public void setReceiveAddress(String receiveAddress) {
        this.receiveAddress = receiveAddress;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", realName='" + realName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", identityCardNumber='" + identityCardNumber + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", homeAddress='" + homeAddress + '\'' +
                ", receiveAddress='" + receiveAddress + '\'' +
                ", status=" + status +
                ", loginTime=" + loginTime +
                ", createTime=" + createTime +
                '}';
    }
}
