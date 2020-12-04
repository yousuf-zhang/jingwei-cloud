package com.vastmoon.sparrow.security.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

/**
 * <p> ClassName: SparrowSecurityAutoConfig
 * <p> Description: 自动配置类
 *
 * @author yousuf 2020/11/30
 */
public class SparrowSecurityAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty("spring.security.oauth2.resourceserver.jwt.jwk-set-uri")
    JwtDecoder jwtDecoder(OAuth2ResourceServerProperties serverProperties) {
        return NimbusJwtDecoder.withJwkSetUri(serverProperties.getJwt().getJwkSetUri()).build();
    }
}
