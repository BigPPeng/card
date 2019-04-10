package com.card.model.request;

/**
 * Created by hongpeng.cui on 2019/4/10.
 */
public class BillChartMonthRequest {

    private long userId;
    private int consumeType;
    private int yearBegin;
    private int yearEnd;
    private int monthBegin;
    private int monthEnd;


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(int consumeType) {
        this.consumeType = consumeType;
    }

    public int getYearBegin() {
        return yearBegin;
    }

    public void setYearBegin(int yearBegin) {
        this.yearBegin = yearBegin;
    }

    public int getYearEnd() {
        return yearEnd;
    }

    public void setYearEnd(int yearEnd) {
        this.yearEnd = yearEnd;
    }

    public int getMonthBegin() {
        return monthBegin;
    }

    public void setMonthBegin(int monthBegin) {
        this.monthBegin = monthBegin;
    }

    public int getMonthEnd() {
        return monthEnd;
    }

    public void setMonthEnd(int monthEnd) {
        this.monthEnd = monthEnd;
    }
}
