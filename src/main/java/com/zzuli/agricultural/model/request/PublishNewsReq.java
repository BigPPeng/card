package com.zzuli.agricultural.model.request;

import lombok.Data;

@Data
public class PublishNewsReq {
    private int publisherId;
    private String title;
    private String keywords;
    private String content;
    private String source;
}
