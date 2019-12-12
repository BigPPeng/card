package com.card.model.response;


public class BaseResponse {

    private int retCode;
    private String message;

    public BaseResponse(int retCode, String message) {
        this.retCode = retCode;
        this.message = message;
    }

    public BaseResponse() {
    }

    public int getRetCode() {
        return retCode;
    }

    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
