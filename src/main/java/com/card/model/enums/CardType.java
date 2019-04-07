package com.card.model.enums;

/**
 * Created by hongpeng.cui on 2019/4/2.
 */
public enum CardType {
    PU_TONG(1,"普通"),
    BAI_YIN(2,"白银"),
    HUANG_JIN(3,"黄金"),
    BO_JIN(4,"铂金"),
    XING_ZUAN(5,"星钻"),
    ;

    public int type;
    public String desc;

    CardType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static CardType getById(int id) {
        for (CardType cardType : CardType.values()) {
            if (cardType.type == id)
                return cardType;
        }
        return PU_TONG;
    }

}
