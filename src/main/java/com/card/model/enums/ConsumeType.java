package com.card.model.enums;

import java.util.Random;

/**
 * 消费类型
 * Created by hongpeng.cui on 2019/3/27.
 */
public enum ConsumeType {
    CAN_YIN(1,"餐饮"),
    JIAO_TONG(2,"交通"),
    YU_LE(3,"娱乐"),
    GOU_WU(4,"购物"),
    TONG_XUN(5,"通信"),
    HONG_BAO(6,"红包"),
    ZU_FANG(7,"租房"),
    YI_LIAO(8,"医疗"),
    QI_TA(99,"其他"),
    ;

    public int type;
    public String desc;

    ConsumeType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }


    public static ConsumeType getRandom(int i){
        if (i<0) {
            int a = new Random().nextInt() % 10;
            while (a < 0) {
                a = new Random().nextInt() % 10;
            }
            if (a == 9)
                return QI_TA;
            else
                return ConsumeType.values()[a];
        }
        return ConsumeType.values()[i];
    }

}
