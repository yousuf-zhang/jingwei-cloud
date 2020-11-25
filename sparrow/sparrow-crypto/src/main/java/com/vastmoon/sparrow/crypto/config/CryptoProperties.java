package com.vastmoon.sparrow.crypto.config;

import com.vastmoon.sparrow.crypto.aes.AesProperties;
import com.vastmoon.sparrow.crypto.rsa.RsaProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * <p> ClassName: CryptoConfiguration
 * <p> Description: 自动配置文件
 *
 * @author yousuf 2020/2/26
 */
@ConfigurationProperties(prefix = "vastmoon")
@Data
public class CryptoProperties {

    @NestedConfigurationProperty
    private RsaProperties rsa = new RsaProperties();

    @NestedConfigurationProperty
    private AesProperties aes = new AesProperties();
}
