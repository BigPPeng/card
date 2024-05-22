package com.zzuli.agricultural.model.request;

import lombok.Data;

@Data
public class PublishConsultationReq {
    private int publisherId;
    private String consultationTitle;
    private String consultationContent;
    private String consultationKeywords;
}
