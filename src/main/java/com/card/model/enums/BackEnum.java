package com.card.model.enums;

/**
 * Created by hongpeng.cui on 2019/4/2.
 */
public enum BackEnum {
    PU_FA_YIN_HANG(1,"浦发银行"),
    JIAN_SHE_YIN_HANG(2,"建设银行"),
    GONG_SHANG_YIN_HANG(3,"工商银行"),
    MIN_SHENG_YIN_HANG(4,"民生银行"),
    ZHAO_SHANG_YIN_HANG(5,"招商银行"),
    GUANG_FA_YIN_HANG(6,"广发银行"),
    GUANG_DA_YIN_HANG(7,"光大银行"),
    JIA0_TONG_YIN_HANG(8,"交通银行")
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
