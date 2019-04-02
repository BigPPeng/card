package com.card.model.enums;

/**
 * Created by hongpeng.cui on 2019/4/2.
 */
public enum BackEnum {
    JIAN_SHE_YIN_HANG(1,"建设银行"),
    GONG_SHANG_YIN_HANG(2,"工商银行"),
    NONG_YE_YIN_HANG(3,"农业银行"),
    ZHAO_SAHNG_YIN_HANG(4,"招商银行"),
    GUANG_DA_YIN_HANG(5,"广大银行")
    ;
    public int id;
    public String name;

    BackEnum(int id, String desc) {
        this.id = id;
        this.name = desc;
    }
    public static BackEnum getById(int id) {
        for (BackEnum backEnum : BackEnum.values()) {
            if (backEnum.id == id)
                return backEnum;
        }
        return null;
    }

}
