package com.card.model.request;

/**
 * Created by hongpeng.cui on 2019/4/2.
 */
public class GetCardRequest {
    private Long cardNumber;// 卡号
    private int backId;// 银行ID
    private Long userId;

    public Long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getBackId() {
        return backId;
    }

    public void setBackId(int backId) {
        this.backId = backId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
