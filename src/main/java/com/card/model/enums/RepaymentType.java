package com.card.model.enums;

/**
 * 还款方式
 * Created by hongpeng.cui on 2019/3/27.
 */
public enum RepaymentType {
    WEI_HUAN_ZHANG(0,"未还"),
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


    public static RepaymentType getById(int id) {
        for (RepaymentType repaymentType : RepaymentType.values()) {
            if (repaymentType.type == id)
                return repaymentType;
        }
        return QI_TA;
    }

}
