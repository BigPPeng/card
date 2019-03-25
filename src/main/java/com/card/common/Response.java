package com.card.common;

/**
 * 通用返回结构体
 *
 * Created by hongpeng.cui on 2019/3/25.
 */
public class Response<T> {
    private String message;

    private int status;

    private T data;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
