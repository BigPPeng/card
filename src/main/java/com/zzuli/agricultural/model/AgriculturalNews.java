package com.zzuli.agricultural.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 农业资讯
 */
@Builder
@Data
@Slf4j
public class AgriculturalNews {
    private Integer id;
    private String title;
    private Integer publishTime;
    private String publishKeywords;
    private String publisher;
    private String source;
    private String content;
}

