package com.card.common.properties;

import lombok.Data;

/**
 * Created by cuihp on 2020/4/26.
 */
@Data
public class ShiroProperties {
    private long sessionTimeout;
    private int cookieTimeout;
    private String anonUrl;
    private String loginUrl;
    private String successUrl;
    private String logoutUrl;
    private String unauthorizedUrl;
}
