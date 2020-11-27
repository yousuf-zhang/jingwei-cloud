package com.vastmoon.woodpecker.auth;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.vastmoon.sparrow.jpa.BaseRepositoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * <p> ClassName: BootstrapApplication
 * <p> Description: 启动类
 *
 * @author yousuf 2020/11/16
 */
@EnableCaching
@EnableDiscoveryClient
@SpringBootApplication
@EnableEncryptableProperties
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class BootstrapApplication {
    public static void main(String[] args) {
        SpringApplication.run(BootstrapApplication.class, args);
    }
}
