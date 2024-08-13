//package com.card.config;
//
//import com.card.common.CardProperties;
//import com.card.config.shiro.ShiroRealm;
//import org.apache.shiro.mgt.SecurityManager;
//import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
//import org.apache.shiro.util.StringUtils;
//import org.apache.shiro.web.mgt.CookieRememberMeManager;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.servlet.Cookie;
//import org.apache.shiro.web.servlet.SimpleCookie;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.Resource;
//import javax.servlet.Filter;
//import java.util.LinkedHashMap;
//import java.util.Map;
//
///**
// * shiro 权限认证插架配置
// */
//@Configuration
//public class ShiroConfig {
//    private static final String ANON = "anon";// 所有url都都可以匿名访问
//    private static final String AUTHC = "authc";// 需要认证才能进行访问
//    private static final String USER = "user";// 配置记住我或认证通过可以访问logout
//    private static final String LOGOUT = "logout";// 退出登录
//
//    @Resource
//    private CardProperties cardProperties;
//
//
//    @Bean
//    private ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager){
//        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
//        Map<String,Filter> filter = shiroFilterFactoryBean.getFilters();
//        shiroFilterFactoryBean.setSecurityManager(securityManager);
//        // 登录的 url
//        shiroFilterFactoryBean.setLoginUrl(cardProperties.getShiro().getLoginUrl());
//        // 登录成功后跳转的 url
//        shiroFilterFactoryBean.setSuccessUrl(cardProperties.getShiro().getSuccessUrl());
//        // 未授权 url
//        shiroFilterFactoryBean.setUnauthorizedUrl(cardProperties.getShiro().getUnauthorizedUrl());
//
//        LinkedHashMap<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
//        String[] anonUrls = StringUtils.split(cardProperties.getShiro().getAnonUrl(),',');
//        for (String anonUrl : anonUrls) {
//            filterChainDefinitionMap.put(anonUrl,ANON);
//        }
//        // 配置退出过滤器，其中具体的退出代码 Shiro已经替我们实现了
//        filterChainDefinitionMap.put(cardProperties.getShiro().getLogoutUrl(), "logout");
//
//        // 除上以外所有 url都必须认证通过才可以访问，未通过认证自动访问 LoginUrl
//        filterChainDefinitionMap.put("/**", "user");
//
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
//        return shiroFilterFactoryBean;
//    }
//
//    @Bean
//    public SecurityManager securityManager(ShiroRealm shiroRealm) {
//        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        securityManager.setRealm(shiroRealm);
//        securityManager.setRememberMeManager(rememberMeManager());
//        return securityManager;
//    }
//
//
//    private CookieRememberMeManager rememberMeManager(){
//        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
//        cookieRememberMeManager.setCookie(rememberMeCookie());
//        return cookieRememberMeManager;
//    }
//
//    private Cookie rememberMeCookie() {
//        // 设置 cookie 名称，对应 login.html 页面的 <input type="checkbox" name="rememberMe"/>
//        SimpleCookie cookie = new SimpleCookie("rememberMe");
//        // 设置 cookie 的过期时间，单位为秒，这里为一天
//        cookie.setMaxAge(cardProperties.getShiro().getCookieTimeout());
//        return cookie;
//    }
//
//
//}
