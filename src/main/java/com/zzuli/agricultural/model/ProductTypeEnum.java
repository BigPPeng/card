package com.zzuli.agricultural.model;

public enum ProductTypeEnum {
    nong_yao(1, "农药"),
    hua_fei(2, "化肥"),
    zhong_zi(3, "种子"),
    nong_ju(4, "农具"),
    ;

    public int type;
    public String desc;

    ProductTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }


    public static ProductTypeEnum getById(int id) {
        for (ProductTypeEnum value : ProductTypeEnum.values()) {
            if (value.type == id) {
                return value;
            }
        }
        return ProductTypeEnum.nong_ju;
    }

}
