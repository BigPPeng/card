package com.zzuli.agricultural.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 专家咨询信息
 */
@Builder
@Data
@Slf4j
public class ExpertConsultation {
    private Integer id;
    private Integer consultantId;
    private String consultantName;
    private Integer consultationTime;
    private String consultationTitle;
    private String consultationContent;
    private String consultationKeywords;
    private Integer responderId;
    private String responderName;
    private Integer responseTime;
    private String responseContent;
}
