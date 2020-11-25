package com.vastmoon.sparrow.crypto.config;

import com.vastmoon.sparrow.crypto.aes.AesHelper;
import com.vastmoon.sparrow.crypto.aes.AesKeyService;
import com.vastmoon.sparrow.crypto.aes.DefaultAesKeyServiceImpl;
import com.vastmoon.sparrow.crypto.rsa.RsaHelper;
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
    @ConditionalOnProperty(prefix = "vastmoon.rsa", name = "enabled", havingValue = "true")
    public RsaHelper rsaHelper(CryptoProperties properties) {
        log.info("init default RsaHelper");
        return new RsaHelper(properties.getRsa());
    }

    @Bean
    @ConditionalOnMissingBean
    @DependsOn("aesKeyService")
    public AesHelper aesHelper(CryptoProperties properties, AesKeyService aesKeyService) {
        log.info("init default AesHelper");
        return new AesHelper(properties.getAes(), aesKeyService);
    }

    @Bean(name = AesKeyService.AES_KEY_SERVICE_NAME)
    @ConditionalOnMissingBean
    public AesKeyService aesKeyService() {
        log.info("init default AesKeyService");
        return new DefaultAesKeyServiceImpl();
    }

}
