package com.card.model.response;

import java.util.Map;

/**
 * Created by hongpeng.cui on 2019/4/10.
 */
public class BillRecordChartResponse {
    private int year;
    private int month;
    private Map<Integer,String> consumeDesc; // key：是消费类型ID，value是描述
    private Map<Integer,Double> chartInfo;// key: 消费类型ID，value:是金额  这两个map的key一致

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

    public Map<Integer, Double> getChartInfo() {
        return chartInfo;
    }

    public void setChartInfo(Map<Integer, Double> chartInfo) {
        this.chartInfo = chartInfo;
    }

    public Map<Integer, String> getConsumeDesc() {
        return consumeDesc;
    }

    public void setConsumeDesc(Map<Integer, String> consumeDesc) {
        this.consumeDesc = consumeDesc;
    }
}
