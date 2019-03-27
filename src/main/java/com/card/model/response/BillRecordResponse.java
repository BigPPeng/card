package com.card.model.response;

import com.card.model.BillRecord;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 账单信息查询返回结构体
 *
 * Created by hongpeng.cui on 2019/3/27.
 */
public class BillRecordResponse {
    private int pageSize;
    private int pageNumber;
    private int totalPage;
    private int totalCount;
    private List<BillRecord> billRecord = Lists.newArrayList();

    public BillRecordResponse() {
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<BillRecord> getBillRecord() {
        return billRecord;
    }

    public void setBillRecord(List<BillRecord> billRecord) {
        this.billRecord = billRecord;
    }
}
