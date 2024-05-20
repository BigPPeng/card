package com.zzuli.agricultural.model.request;

import lombok.Data;

import java.util.List;

@Data
public class ListReq <T>{
    private List<T> list;
}
