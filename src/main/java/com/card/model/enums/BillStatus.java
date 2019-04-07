package com.card.model.enums;

/**
 * 账单状态
 * Created by hongpeng.cui on 2019/3/27.
 */
public enum BillStatus {
    WEI_CHU_ZHANG(1,"未出账"),
    CHU_ZHANG(2,"已出账"),
    YAN_QI(3,"延期"),
    HUAN_KUAN(4,"已经还款"),
    QU_XIAO(5,"账单取消"),
    SHAN_CHU(6,"账单删除"),
    YI_CHANG(99,"异常"),
    ;

    public int status;
    public String desc;

    BillStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static BillStatus getById(int status) {
        for (BillStatus billStatus : BillStatus.values()) {
            if (billStatus.status == status)
                return billStatus;
        }
        return YI_CHANG;
    }


}
