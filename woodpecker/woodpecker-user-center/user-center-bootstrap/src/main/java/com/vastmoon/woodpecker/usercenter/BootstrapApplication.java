package com.vastmoon.woodpecker.usercenter;

import com.vastmoon.sparrow.security.annotation.EnableSparrowResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * <p> ClassName: BootstrapApplication
 * <p> Description: 启动类
 *
 * @author yousuf 2020/11/16
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableSparrowResourceServer
public class BootstrapApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootstrapApplication.class, args);
    }
}
