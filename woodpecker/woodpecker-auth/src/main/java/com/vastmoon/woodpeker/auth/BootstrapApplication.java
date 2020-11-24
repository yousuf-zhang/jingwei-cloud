package com.vastmoon.woodpeker.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * <p> ClassName: BootstrapApplication
 * <p> Description: 启动类
 *
 * @author yousuf 2020/11/16
 */
@EnableDiscoveryClient
@SpringBootApplication
public class BootstrapApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootstrapApplication.class, args);
    }
}
