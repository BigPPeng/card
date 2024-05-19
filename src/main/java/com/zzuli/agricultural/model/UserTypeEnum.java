package com.zzuli.agricultural.model;

public enum UserTypeEnum {
    //Farmers, agricultural technology experts, administrators,
    farmers(1,"农户"),
    agricultural_technology_experts(2,"专家"),
    administrators(3,"管理员"),
    Store_owner(4,"商店老板"),
    buyer(5,"收购商"),
    ;

    public int type;
    public String desc;

    UserTypeEnum(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public static UserTypeEnum getById(int id) {
        for (UserTypeEnum value : UserTypeEnum.values()) {
            if (value.type == id) {
                return value;
            }
        }
        return UserTypeEnum.farmers;
    }

}
