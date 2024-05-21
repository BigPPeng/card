package com.zzuli.agricultural.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 招工信息
 */
@Builder
@Data
@Slf4j
public class RecruitmentInfo {
    private Integer id;
    private String recruitmentTitle;
    private Integer recruitmentTime;
    private Integer recruitmentQuantity;
    private String jobDescription;
    private Integer recruiterUserId;
    private Integer publishTime;
}
