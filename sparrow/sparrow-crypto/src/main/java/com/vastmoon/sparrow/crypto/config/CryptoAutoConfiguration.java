package com.vastmoon.sparrow.crypto.config;

import com.vastmoon.sparrow.crypto.aes.AESManager;
import com.vastmoon.sparrow.crypto.aes.AESKeyService;
import com.vastmoon.sparrow.crypto.rsa.RSAManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * <p> ClassName: CryptoAutoConfiguration
 * <p> Description: 加密算法初始化
 *
 * @author yousuf 2020/2/26
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(CryptoProperties.class)
public class CryptoAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public RSAManager rsaManager(CryptoProperties properties) {
        log.info("init default RSAManager");
        return new RSAManager(properties.getRsa());
    }

    @Bean
    @ConditionalOnMissingBean
    @DependsOn(AESKeyService.AES_KEY_SERVICE_NAME)
    public AESManager aesManager(CryptoProperties properties, AESKeyService aesKeyService) {
        log.info("init default AesHelper");
        return new AESManager(properties.getAes(), aesKeyService);
    }

    @Bean(name = AESKeyService.AES_KEY_SERVICE_NAME)
    @ConditionalOnMissingBean
    public AESKeyService aesKeyService() {
        log.info("init default AesKeyService");
        return name -> {
            log.info("默认实现" + name );
            return null;
        };
    }

}
