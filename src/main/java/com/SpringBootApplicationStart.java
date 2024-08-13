package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 项目启动类
 *
 * Created by hongpeng.cui on 2018/12/26.
 */
@SpringBootApplication(scanBasePackages = {"com.zzuli"})
public class SpringBootApplicationStart {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringBootApplicationStart.class);
        application.run(args);
    }
}
