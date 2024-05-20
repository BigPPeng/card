package com.zzuli.agricultural.model;

public enum RentSaleType {
    Rent(1, "出租"),
    Sale(2, "售卖"),
    ;

    public int type;
    public String desc;

    RentSaleType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static RentSaleType getById(int id) {
        for (RentSaleType value : RentSaleType.values()) {
            if (value.type == id) {
                return value;
            }
        }
        return RentSaleType.Sale;
    }
}
