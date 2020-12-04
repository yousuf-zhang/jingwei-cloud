package com.vastmoon.woodpecker.auth.config;

import com.vastmoon.sparrow.core.exception.framework.FrameworkException;
import com.vastmoon.sparrow.crypto.rsa.RSAManager;
import com.vastmoon.sparrow.crypto.rsa.RSAStore;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;

/**
 * <p> ClassName: KeyConfig
 * <p> Description: key配置
 *
 * @author yousuf 2020/11/24
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final static String JWT_RSA_KEY="jwt_rsa_key";
    private final RSAManager rsaManager;
    @Bean
    public KeyPair keyPair() {
        try {
            RSAStore jwtStore = rsaManager.get(JWT_RSA_KEY)
                    .orElseThrow(() -> new FrameworkException("jwt rsa key not find"));
            return new KeyPair(jwtStore.publicKey(), jwtStore.privateKey());
        } catch ( Exception e ) {
            throw new IllegalArgumentException(e);
        }
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey((RSAPublicKey) keyPair().getPublic()).build();
    }
}
