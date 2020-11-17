package com.vastmoon.sparrow.security.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

/**
 * <p> ClassName: SparrowResourceServerConfigurerAdapter
 * <p> Description: 资源服务器配置类
 *
 * @author yousuf 2020/11/12
 */
@Slf4j
@RequiredArgsConstructor
public class SparrowResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {
    private final RemoteTokenServices remoteTokenServices;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId("user:add").tokenServices(remoteTokenServices);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .cors();
    }
}
