package com.vastmoon.woodpechker.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <p> ClassName: ApplicationConfig
 * <p> Description: app配置信息
 *
 * @author yousuf 2020/11/17
 */
@Configuration
public class ApplicationConfig {


    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
