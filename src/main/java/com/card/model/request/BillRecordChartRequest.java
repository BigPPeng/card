package com.card.model.request;

/**
 * Created by hongpeng.cui on 2019/4/10.
 */
public class BillRecordChartRequest {

    private long userId;
    private int year;
    private int month;
    private int consumeType;


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(int consumeType) {
        this.consumeType = consumeType;
    }
}
