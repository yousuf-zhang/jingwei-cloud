package com.vastmoon.peacock.register;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <p> ClassName: BootstrapApplication
 * <p> Description: 启动类
 *
 * @author yousuf 2020/11/16
 */
@EnableEurekaServer
@SpringBootApplication
public class BootstrapApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootstrapApplication.class, args);
    }
}
