package com.card.test.lock;

/**
 * Created by cuihp on 2020/3/6.
 */
public enum CountryEnum {
    ONE(1,"齐国"),
    TWO(2,"楚国"),
    THREE(3,"燕国"),
    FOUR(4,"赵国"),
    FIVE(5,"魏国"),
    SIX(6,"韩国"),
    SEVERN(7,"秦国"),
    ;

    public int id;
    public String name;

    CountryEnum(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static CountryEnum getById(int id) {
        for (CountryEnum countryEnum : CountryEnum.values()) {
            if (countryEnum.id == id) {
                return countryEnum;
            }
        }
        return SEVERN;
    }


}
