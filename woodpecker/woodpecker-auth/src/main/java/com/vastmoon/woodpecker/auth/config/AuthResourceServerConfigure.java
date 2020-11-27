package com.vastmoon.woodpecker.auth.config;

import com.sun.management.VMOption;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * <p> ClassName: AuthResourceServerConfigure
 * <p> Description: 资源服务器配置文件
 *
 * @author yousuf 2020/11/26
 */
@Configuration
@EnableResourceServer
public class AuthResourceServerConfigure extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers(request -> request.mvcMatchers("/**"))
                .authorizeRequests(auth -> auth.mvcMatchers("/**").authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    }
}
