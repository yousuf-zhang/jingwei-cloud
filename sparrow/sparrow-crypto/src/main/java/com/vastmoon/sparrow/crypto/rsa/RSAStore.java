package com.vastmoon.sparrow.crypto.rsa;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import lombok.Getter;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * <p> ClassName: RSAEnhance
 * <p> Description: RSA增强
 *
 * @author yousuf 2020/12/4
 */
public class RSAStore {
    @Getter
    private final String name;
    private final RSA rsa;
    public RSAStore(RSAProperties properties) {
        this.rsa = RSAUtils.generateRsa(properties);
        this.name = properties.getName();

    }


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
    public String decrypt(String data) {
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
    public String encrypt(String data) {
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
    public PrivateKey privateKey() {
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
    public PublicKey publicKey() {
        return rsa.getPublicKey();
    }
}
