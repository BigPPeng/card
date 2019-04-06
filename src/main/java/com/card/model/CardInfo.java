package com.card.model;

/**
 * Created by hongpeng.cui on 2019/4/2.
 */
public class CardInfo {
    /*
    DROP TABLE IF EXISTS `card_info`;
CREATE TABLE `card_info` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(11) NOT NULL COMMENT 'id',
  `user_name` varchar(30) NOT NULL DEFAULT '' COMMENT '用户名',
  `card_type` int(11) NOT NULL DEFAULT '0' COMMENT '卡类型',
  `card_number` varchar(30) NOT NULL DEFAULT '' COMMENT '卡号',
  `card_limit` int(11) NOT NULL DEFAULT '0' COMMENT '卡额度',
  `back_name` varchar(100) NOT NULL DEFAULT '' COMMENT '银行名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `effective_time` datetime DEFAULT null COMMENT '激活时间',
  `repay_time` datetime DEFAULT null COMMENT '激活时间',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '卡状态',
     */

    private Long id;// 信用卡ID
    private Long userId;// 用户ID
    private String userName; // 用户名称
    /**
     * 信用卡类型 枚举{@link com.card.model.enums.CardType}
     */
    private Integer cardType;
    private String cardNumber;// 卡号
    private Integer cardLimit;// 卡限额
    /**
     * 银行名称 枚举{@link com.card.model.enums.BackEnum}
     */
    private String backName;
    private Long createTime;// 创建时间
    private Long effectiveTime;// 生效激活时间
    private Long repayTime;// 还款日期 距离月初的秒数 10号零点 10 * 24 * 60 * 60
    /**
     * 信用卡状态 枚举{@link com.card.model.enums.CardStatus}
     */
    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getCardLimit() {
        return cardLimit;
    }

    public void setCardLimit(Integer cardLimit) {
        this.cardLimit = cardLimit;
    }

    public String getBackName() {
        return backName;
    }

    public void setBackName(String backName) {
        this.backName = backName;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Long effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getRepayTime() {
        return repayTime;
    }

    public void setRepayTime(Long repayTime) {
        this.repayTime = repayTime;
    }
}
