package com.card;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动类
 *
 * Created by hongpeng.cui on 2018/12/26.
 */
@SpringBootApplication
public class SpringBootApplicationStart {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringBootApplicationStart.class);
        application.run(args);
    }
}
