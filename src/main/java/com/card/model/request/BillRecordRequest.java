package com.card.model.request;

import java.util.Date;

/**
 * 账单记录请求
 * Created by hongpeng.cui on 2019/3/27.
 */
public class BillRecordRequest {
    // 必须的
    private Long userId;
    // 非必须筛选条件
    private Integer pageSize;
    private Integer pageNumber;
    private Long cardId;
    private Date consumeBegin;
    private Date consumeEnd;
    private Integer billStatus;
    private Integer repaymentType;
    private Integer consumeType;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
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

    public Integer getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(Integer billStatus) {
        this.billStatus = billStatus;
    }

    public Integer getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(Integer repaymentType) {
        this.repaymentType = repaymentType;
    }

    public Integer getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(Integer consumeType) {
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
