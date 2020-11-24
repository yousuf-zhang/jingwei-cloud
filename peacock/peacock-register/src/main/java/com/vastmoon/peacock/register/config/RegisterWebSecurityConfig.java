package com.vastmoon.peacock.register.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * <p> ClassName: RegisterWebSecurityConfig
 * <p> Description: 注册服务配置
 *
 * @author yousuf 2020/11/17
 */
@Configuration
public class RegisterWebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/eureka/**");
        super.configure(http);
    }
}
