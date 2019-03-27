package com.card.model.enums;

/**
 * 还款方式
 * Created by hongpeng.cui on 2019/3/27.
 */
public enum RepaymentType {
    WE_XIN(1,"微信"),
    ZHI_FU_BAO(2,"支付宝"),
    YIN_HANG(3,"银行卡"),
    QI_TA(99,"其他"),
    ;
    public int type;
    public String desc;

    RepaymentType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }
}
