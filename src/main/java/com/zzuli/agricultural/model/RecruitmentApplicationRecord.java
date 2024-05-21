package com.zzuli.agricultural.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 招工报名信息
 */
@Builder
@Data
@Slf4j
public class RecruitmentApplicationRecord {
    private Integer id;
    private Integer recruitmentInfoId;
    private Integer applicantId;
    private String applicantName;
    private Integer applicationTime;
}
