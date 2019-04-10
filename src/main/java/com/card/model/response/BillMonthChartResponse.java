package com.card.model.response;

import java.util.Map;

/**
 * Created by hongpeng.cui on 2019/4/10.
 */
public class BillMonthChartResponse {
    private int consumeType;
    private String consumeDesc;
    private Map<String,Double> chartInfo;// key: year_month，value:是金额  2019_1


    public int getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(int consumeType) {
        this.consumeType = consumeType;
    }

    public String getConsumeDesc() {
        return consumeDesc;
    }

    public void setConsumeDesc(String consumeDesc) {
        this.consumeDesc = consumeDesc;
    }

    public Map<String, Double> getChartInfo() {
        return chartInfo;
    }

    public void setChartInfo(Map<String, Double> chartInfo) {
        this.chartInfo = chartInfo;
    }
}
