package com.vastmoon.peacock.zuul.gateway.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <p> ClassName: GatewaySecurityConfig
 * <p> Description: 配置文件
 *
 * @author yousuf 2020/11/27
 */
@EnableWebSecurity
public class GatewaySecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }
}
