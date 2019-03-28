package com.card.model.request;

import java.util.Date;

/**
 * 账单记录请求
 * Created by hongpeng.cui on 2019/3/27.
 */
public class BillRecordRequest {
    // 必须的
    private long userId;
    // 非必须筛选条件
    private int pageSize;
    private int pageNumber;
    private long cardId;
    private Date consumeBegin;
    private Date consumeEnd;
    private int billStatus;
    private int repaymentType;
    private int consumeType;

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public Date getConsumeBegin() {
        return consumeBegin;
    }

    public void setConsumeBegin(Date consumeBegin) {
        this.consumeBegin = consumeBegin;
    }

    public Date getConsumeEnd() {
        return consumeEnd;
    }

    public void setConsumeEnd(Date consumeEnd) {
        this.consumeEnd = consumeEnd;
    }

    public int getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(int billStatus) {
        this.billStatus = billStatus;
    }

    public int getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(int repaymentType) {
        this.repaymentType = repaymentType;
    }

    public int getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(int consumeType) {
        this.consumeType = consumeType;
    }

    @Override
    public String toString() {
        return "BillRecordRequest{" +
                "pageSize=" + pageSize +
                ", pageNumber=" + pageNumber +
                ", userId=" + userId +
                ", cardId=" + cardId +
                ", consumeBegin=" + consumeBegin +
                ", consumeEnd=" + consumeEnd +
                ", billStatus=" + billStatus +
                ", repaymentType=" + repaymentType +
                ", consumeType=" + consumeType +
                '}';
    }
}
