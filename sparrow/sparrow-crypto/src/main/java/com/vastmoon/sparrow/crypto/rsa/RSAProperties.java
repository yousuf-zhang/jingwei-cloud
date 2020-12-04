package com.vastmoon.sparrow.crypto.rsa;

import com.ulisesbocchio.jasyptspringboot.util.AsymmetricCryptography;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <p> ClassName: RsaProperties
 * <p> Description: RSA配置文件
 *
 * @author yousuf 2020/2/26
 */
@Data
public class RSAProperties {
    @NotEmpty
    private String name;
    private String privateKeyPath;
    private String publicKeyPath;
    private AsymmetricCryptography.KeyFormat keyFormat = AsymmetricCryptography.KeyFormat.DER;
    /**秘钥长度*/
    private Integer keySize = 2048;

}
