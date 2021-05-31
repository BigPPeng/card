package com.test2.java;

import java.util.List;

public class SearchDB implements Search{
    @Override
    public List<String> search(String keyWord) {
        System.out.println("Search DB "+keyWord);
        return null;
    }
}
