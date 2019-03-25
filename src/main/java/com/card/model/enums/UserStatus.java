package com.card.model.enums;

/**
 * Created by hongpeng.cui on 2019/3/25.
 */
public enum UserStatus {
    DEFAULT(0,"默认"),
    OK(1,"已经实名"),
    LIMIT(-1,"受限");

    public int status;
    public String desc;

    UserStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
