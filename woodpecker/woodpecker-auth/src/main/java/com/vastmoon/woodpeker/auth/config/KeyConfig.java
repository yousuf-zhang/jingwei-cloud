package com.vastmoon.woodpeker.auth.config;

import com.vastmoon.sparrow.crypto.rsa.RsaHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.KeyPair;

/**
 * <p> ClassName: KeyConfig
 * <p> Description: key配置
 *
 * @author yousuf 2020/11/24
 */
@Configuration
@RequiredArgsConstructor
public class KeyConfig {
    @Bean
    @DependsOn("rsaHelper")
    public KeyPair keyPair() {
        try {
            return new KeyPair(RsaHelper.publicKey(), RsaHelper.privateKey());
        } catch ( Exception e ) {
            throw new IllegalArgumentException(e);
        }
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
