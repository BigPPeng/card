package com.card.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by hongpeng.cui on 2019/3/24.
 */
@Configuration
public class MyWebConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/asset/**").addResourceLocations("classpath:/asset/");
        super.addResourceHandlers(registry);
    }
}
