package com.vastmoon.sparrow.crypto.rsa;

import com.ulisesbocchio.jasyptspringboot.util.AsymmetricCryptography;
import lombok.Data;

/**
 * <p> ClassName: RsaProperties
 * <p> Description: RSA配置文件
 *
 * @author yousuf 2020/2/26
 */
@Data
public class RsaProperties {
    /**是否开启rsa 默认关闭*/
    private boolean enabled = false;
    private String privateKeyPath;
    private String publicKeyPath;
    private AsymmetricCryptography.KeyFormat keyFormat = AsymmetricCryptography.KeyFormat.DER;
    /**秘钥长度*/
    private Integer keySize = 2048;

}
