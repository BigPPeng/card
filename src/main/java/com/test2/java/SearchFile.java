package com.test2.java;

import java.util.List;

public class SearchFile implements Search{
    @Override
    public List<String> search(String keyWord) {
        System.out.println("Search File "+keyWord);
        return null;
    }
}
