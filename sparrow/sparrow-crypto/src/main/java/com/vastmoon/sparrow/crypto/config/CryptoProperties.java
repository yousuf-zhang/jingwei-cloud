package com.vastmoon.sparrow.crypto.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.vastmoon.sparrow.crypto.aes.AesProperties;
import com.vastmoon.sparrow.crypto.rsa.RSAProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

/**
 * <p> ClassName: CryptoConfiguration
 * <p> Description: 自动配置文件
 *
 * @author yousuf 2020/2/26
 */
@Data
@Validated
@ConfigurationProperties(prefix = "vastmoon")
public class CryptoProperties {
    @Valid
    private Set<RSAProperties> rsa = Sets.newHashSet();
    @Valid
    private Set<AesProperties> aes = Sets.newHashSet();
}
