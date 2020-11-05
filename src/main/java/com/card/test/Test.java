package com.card.test;

import java.util.EnumSet;

/**
 * Created by cuihp on 2020/4/22.
 */
public class Test {
    public static void main(String[] args) {
        EnumSet<TestEnum> testEnums = EnumSet.allOf(TestEnum.class);
        testEnums.forEach((TestEnum testEnum) -> {
            System.out.println(testEnum.a + "___" + testEnum.i);
        });


    }



    public enum TestEnum {
        A(1,"a"),
        B(2,"b"),
        C(3,"c"),
        D(4,"d")
        ;

        int i;
        String a;

        TestEnum(int i, String a) {
            this.i = i;
            this.a = a;
        }
    }



}
