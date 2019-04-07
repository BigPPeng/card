package com.card.model;

import com.card.common.DateToolUtil;
import com.card.model.enums.BillStatus;
import com.card.model.enums.ConsumeType;
import com.card.model.enums.RepaymentType;

/**
 * 账单信息
 *
 * Created by hongpeng.cui on 2019/3/27.
 */
public class BillRecord {
    /*
       `bill_record` (
      `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
      `user_id` bigint(11) NOT NULL DEFAULT -1  COMMENT '用户ID',
      `card_id` bigint(30) NOT NULL DEFAULT -1 COMMENT '关联信用卡ID',
      `consume_time` bigint(30) NOT NULL DEFAULT -1 COMMENT '消费日期，存储距离1970..秒数',
      `bill_status` int(11) NOT NULL DEFAULT 0 COMMENT '账单状态',
      `consume_type` int(11) NOT NULL DEFAULT 0 COMMENT '消费类型',
      `repayment_time` bigint(30) NOT NULL DEFAULT -1 COMMENT '还款日期',
      `money` bigint(30) NOT NULL DEFAULT -1 COMMENT '金额',
      `repayment_type` int(11) NOT NULL DEFAULT -1 COMMENT '还款方式',
      `create_time` datetime NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
     */

    private Long billRecordId;
    private Long userId;
    private Long cardId;//账单关联的信用卡ID
    private Long consumeTime;// 消费日期
    private String consumeTimeString;
    /**
     * 账单状态{@link BillStatus}
     */
    private Integer billStatus;
    private String billStatusString;
    /**
     * 消费类型{@link ConsumeType}
     */
    private Integer consumeType;
    private String consumeTypeString;

    private Long repaymentTime;// 还款日期或者最后还款日期
    private String repaymentTimeString;

    private Double money;// 金额
    /**
     * 还款方式{@link RepaymentType}
     */
    private Integer repaymentType;
    private String repaymentTypeString;

    private Long createTime;// 账单创建时间
    private String createTimeString;

    public Long getBillRecordId() {
        return billRecordId;
    }

    public void setBillRecordId(Long billRecordId) {
        this.billRecordId = billRecordId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Long getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(Long consumeTime) {
        this.consumeTime = consumeTime;
    }

    public Integer getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(Integer billStatus) {
        this.billStatus = billStatus;
    }

    public Integer getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(Integer consumeType) {
        this.consumeType = consumeType;
    }

    public Long getRepaymentTime() {
        return repaymentTime;
    }

    public void setRepaymentTime(Long repaymentTime) {
        this.repaymentTime = repaymentTime;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Integer getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(Integer repaymentType) {
        this.repaymentType = repaymentType;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getConsumeTimeString() {
        return DateToolUtil.format("yyyy-MM-dd HH:mm:ss",consumeTime);
    }

    public void setConsumeTimeString(String consumeTimeString) {
        this.consumeTimeString = consumeTimeString;
    }

    public String getBillStatusString() {
        billStatusString = BillStatus.getById(billStatus).desc;
        return billStatusString;
    }

    public void setBillStatusString(String billStatusString) {
        this.billStatusString = billStatusString;
    }

    public String getConsumeTypeString() {
        return ConsumeType.getById(consumeType).desc;
    }

    public void setConsumeTypeString(String consumeTypeString) {
        this.consumeTypeString = consumeTypeString;
    }

    public String getRepaymentTimeString() {
        return DateToolUtil.format("yyyy-MM-dd HH:mm:ss",repaymentTime);
    }

    public void setRepaymentTimeString(String repaymentTimeString) {
        this.repaymentTimeString = repaymentTimeString;
    }

    public String getRepaymentTypeString() {
        return RepaymentType.getById(repaymentType).desc;
    }

    public void setRepaymentTypeString(String repaymentTypeString) {
        this.repaymentTypeString = repaymentTypeString;
    }

    public String getCreateTimeString() {
        return DateToolUtil.format("yyyy-MM-dd HH:mm:ss",createTime);
    }

    public void setCreateTimeString(String createTimeString) {
        this.createTimeString = createTimeString;
    }

    @Override
    public String toString() {
        return "BillRecord{" +
                "billRecordId=" + billRecordId +
                ", userId=" + userId +
                ", cardId=" + cardId +
                ", consumeTime=" + consumeTime +
                ", billStatus=" + billStatus +
                ", consumeType=" + consumeType +
                ", repaymentTime=" + repaymentTime +
                ", money=" + money +
                ", repaymentType=" + repaymentType +
                ", createTime=" + createTime +
                '}';
    }
}
