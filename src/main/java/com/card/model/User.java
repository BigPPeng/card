package com.card.model;

import com.card.common.DateToolUtil;
import com.card.model.enums.UserStatus;

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
     `pass` varchar(30) NOT NULL DEFAULT '' COMMENT '用户密码',
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

    public User() {
        init();
    }

    private void init() {
        this.realName = "";
        this.identityCardNumber = "";
        this.homeAddress = "";
        this.receiveAddress = "";
        this.status = UserStatus.DEFAULT.status;
    }

    private long id;
    private String name;
    private String pass;
    private String realName;
    private String phoneNumber;
    private String identityCardNumber;
    private String userEmail;
    private String homeAddress;
    private String receiveAddress;
    private int status;
    private Date loginTime;
    private String loginTimeString;
    private Date createTime;
    private String createTimeString;

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


    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    public String getLoginTimeString() {
        if (loginTime == null)
            return "";
        loginTimeString = DateToolUtil.format("yyyy-MM-dd HH:mm:ss", loginTime);
        return loginTimeString;
    }

    public void setLoginTimeString(String loginTimeString) {
        this.loginTimeString = loginTimeString;
    }

    public String getCreateTimeString() {
        if (createTime == null)
            return "";
        createTimeString = DateToolUtil.format("yyyy-MM-dd HH:mm:ss", createTime);
        return createTimeString;
    }

    public void setCreateTimeString(String createTimeString) {
        this.createTimeString = createTimeString;
    }


    public String sessionKey() {
        String s = this.toString();
        return s.hashCode()+s;
    }

}
