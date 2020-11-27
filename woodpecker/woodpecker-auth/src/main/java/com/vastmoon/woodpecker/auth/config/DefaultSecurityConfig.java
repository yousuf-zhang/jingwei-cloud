package com.vastmoon.woodpecker.auth.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * <p> ClassName: WebSecurityConfig
 * <p> Description: 认知服务器配置文件
 *
 * @author yousuf 2020/11/24
 */
@Order(2)
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class DefaultSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.requestMatchers(request -> request.mvcMatchers("/oauth/**", "/.well-known/jwks.json", "/introspect"))
                .authorizeRequests(auth -> auth.mvcMatchers("/.well-known/jwks.json", "/introspect").permitAll()
                        .mvcMatchers("/oauth/**").authenticated())
                .csrf(AbstractHttpConfigurer::disable);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
