package com.vastmoon.sparrow.crypto.rsa;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.asymmetric.AsymmetricAlgorithm;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.ulisesbocchio.jasyptspringboot.util.AsymmetricCryptography;
import com.vastmoon.sparrow.core.common.AbstractSparrowContext;
import lombok.AllArgsConstructor;
import org.springframework.core.io.DefaultResourceLoader;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Objects;

/**
 * <p> ClassName: RsaHelper
 * <p> Description:
 *
 * @author yousuf 2020/2/25
 */
@AllArgsConstructor
public class RsaHelper extends AbstractSparrowContext {
    private static int KEY_SIZE;
    private static RSA rsa;
    private final RsaProperties properties;

    /**
     * <p> Title: decrypt
     * <p> Description: 解密数据
     *
     * @param data 密文
     *
     * @return java.lang.String 明文
     *
     * @author yousuf 2020/2/26
     *
     */
    public static String decrypt(String data) {
        return rsa.decryptStr(data, KeyType.PrivateKey);
    }

    /**
     * <p> Title: encrypt
     * <p> Description: rsa加密数据
     *
     * @param data 明文
     *
     * @return java.lang.String 密文
     *
     * @author yousuf 2020/2/26
     *
     */
    public static String encrypt(String data) {
        return rsa.encryptBase64(data, KeyType.PublicKey);
    }


    /**
     * <p> Title: privateKey
     * <p> Description: 获取私钥
     *
     * @return java.security.PrivateKey
     *
     * @author yousuf 2020/2/27
     *
     */
    public static PrivateKey privateKey() {
        return rsa.getPrivateKey();
    }

    /**
     * <p> Title: publicKey
     * <p> Description: 获取公钥
     *
     * @return java.security.PublicKey
     *
     * @author yousuf 2020/2/27
     *
     */
    public static PublicKey publicKey() {
        return rsa.getPublicKey();
    }
    /**
     * <p> Title: generateKeyPair
     * <p> Description: 生成秘钥
     *
     * @author yousuf 2020/2/26
     *
     */
    public static KeyPair generateKeyPair() {
        return SecureUtil.generateKeyPair(AsymmetricAlgorithm.RSA_ECB_PKCS1.getValue(), KEY_SIZE);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        KEY_SIZE = properties.getKeySize();
        PrivateKey privateKey;
        PublicKey publicKey;
        AsymmetricCryptography cryptography = new AsymmetricCryptography(new DefaultResourceLoader());
        privateKey = cryptography.getPrivateKey(properties.getPrivateKeyPath(), properties.getKeyFormat());
        publicKey = cryptography.getPublicKey(properties.getPublicKeyPath(), properties.getKeyFormat());
        if (Objects.isNull(privateKey) && Objects.isNull(publicKey)) {
            rsa = SecureUtil.rsa();
        } else {
            rsa = SecureUtil.rsa(privateKey.getEncoded(), publicKey.getEncoded());
        }

    }
}

