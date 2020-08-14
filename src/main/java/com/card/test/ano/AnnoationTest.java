package com.card.test.ano;

/**
 * Created by cuihp on 2020/7/5.
 */
public class AnnoationTest {
    @MyAn(min = 10,max = 100,value = "长度在10-100")
    private String name;
    @MyAn(min = 15,max = 30,value = "合法长度在长度在15-30")
    private String pass;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
