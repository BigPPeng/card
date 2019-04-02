package com.card.model.enums;

/**
 * Created by hongpeng.cui on 2019/4/2.
 */
public enum CardStatus {
    NEW(1,"新建"),
    JI_HUO(2,"激活"),
    SUO_DING(3,"锁定"),
    ;

    public int status;
    public String desc;

    CardStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }
}
