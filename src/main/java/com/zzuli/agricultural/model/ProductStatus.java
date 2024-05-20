package com.zzuli.agricultural.model;

public enum ProductStatus {
    valid(0, "有效"),
    inValid(1, "无效"),
    ;

    public int type;
    public String desc;

    ProductStatus(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static ProductStatus getById(int id) {
        for (ProductStatus value : ProductStatus.values()) {
            if (value.type == id) {
                return value;
            }
        }
        return ProductStatus.valid;
    }
}
