package com.vastmoon.sparrow.crypto.rsa;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.RSA;
import com.ulisesbocchio.jasyptspringboot.util.AsymmetricCryptography;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Objects;

/**
 * <p> ClassName: RsaUtils
 * <p> Description: 工具类
 *
 * @author yousuf 2020/11/30
 */
@UtilityClass
public class RSAUtils {

    public RSA generateRsa(RSAProperties properties) {
        AsymmetricCryptography cryptography = new AsymmetricCryptography(new DefaultResourceLoader());
        PrivateKey privateKey = null;
        PublicKey publicKey = null;
        if (StringUtils.isNotBlank(properties.getPrivateKeyPath())) {
            privateKey = cryptography.getPrivateKey(properties.getPrivateKeyPath(), properties.getKeyFormat());
        }
        if (StringUtils.isNotBlank(properties.getPublicKeyPath())) {
            publicKey = cryptography.getPublicKey(properties.getPublicKeyPath(), properties.getKeyFormat());
        }
        byte[] privateKeyEncoded = Objects.isNull(privateKey) ? null : privateKey.getEncoded();
        byte[] publicKeyEncoded = Objects.isNull(publicKey) ? null : publicKey.getEncoded();
        return SecureUtil.rsa(privateKeyEncoded, publicKeyEncoded);
    }

    /**
     * <p> Title: generateKeyPair
     * <p> Description: 生成秘钥
     *
     * @author yousuf 2020/2/26
     *
     */
    public KeyPair generateKeyPair(RSAProperties properties) {
        return SecureUtil.generateKeyPair(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue(), properties.getKeySize());
    }
}
