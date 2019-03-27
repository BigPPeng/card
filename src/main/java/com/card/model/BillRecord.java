package com.card.model;

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

    private long billRecordId;
    private long userId;
    private long cardId;//账单关联的信用卡ID
    private long consumeTime;// 消费日期
    /**
     * 账单状态{@link BillStatus}
     */
    private int billStatus;
    /**
     * 消费类型{@link ConsumeType}
     */
    private int consumeType;
    private long repaymentTime;// 还款日期或者最后还款日期
    private double money;// 金额
    /**
     * 还款方式{@link RepaymentType}
     */
    private int repaymentType;
    private long createTime;// 账单创建时间

    public long getBillRecordId() {
        return billRecordId;
    }

    public void setBillRecordId(long billRecordId) {
        this.billRecordId = billRecordId;
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

    public long getConsumeTime() {
        return consumeTime;
    }

    public void setConsumeTime(long consumeTime) {
        this.consumeTime = consumeTime;
    }

    public int getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(int billStatus) {
        this.billStatus = billStatus;
    }

    public int getConsumeType() {
        return consumeType;
    }

    public void setConsumeType(int consumeType) {
        this.consumeType = consumeType;
    }

    public long getRepaymentTime() {
        return repaymentTime;
    }

    public void setRepaymentTime(long repaymentTime) {
        this.repaymentTime = repaymentTime;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public int getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(int repaymentType) {
        this.repaymentType = repaymentType;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
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
