package com.zzuli.agricultural.model.request;

import lombok.Data;

@Data
public class PublishRecruitmentReq {
    private int recruitmentUserId;
    private String title;
    private String jobDescription;
    private int recruitmentTime;
    private int recruitmentQuantity;
}
