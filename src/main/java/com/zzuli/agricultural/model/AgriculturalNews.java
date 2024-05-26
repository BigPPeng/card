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
    private int publisherId;
    private String source;
    private String content;

    public AgriculturalNews() {
    }

    public AgriculturalNews(Integer id, String title, Integer publishTime, String publishKeywords,
                            String publisher, int publisherId, String source, String content) {
        this.id = id;
        this.title = title;
        this.publishTime = publishTime;
        this.publishKeywords = publishKeywords;
        this.publisher = publisher;
        this.publisherId = publisherId;
        this.source = source;
        this.content = content;
    }
}

