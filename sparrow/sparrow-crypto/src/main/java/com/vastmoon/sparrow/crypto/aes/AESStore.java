package com.vastmoon.sparrow.crypto.aes;


import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.vastmoon.sparrow.core.enumeration.TrueOrFalseEnum;
import lombok.*;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;

/**
 * <p> ClassName: AesKeyStore
 * <p> Description: AesKeyStore
 *
 * @author yousuf 2020/4/28
 */
@Getter
public class AESStore {
    @Getter
    private final String name;
    private final String aesKey;
    private final AES aes;

    public AESStore(String name, String aesKey) {
        this.name = name;
        this.aesKey = aesKey;
        aes =  SecureUtil.aes(Base64.decode(aesKey));
    }

    /**
     * <p> Title: encrypt
     * <p> Description: aes 加密数据
     *
     * @param data 明文
     *
     * @return java.lang.String 密文
     *
     * @author yousuf 2020/2/27
     *
     */
    public String encrypt(String data) {
        return aes.encryptBase64(data);
    }

    /**
     * <p> Title: decrypt
     * <p> Description: 解密数据
     *
     * @param data 密文
     *
     * @return java.lang.String 明文
     *
     * @author yousuf 2020/2/27
     *
     */
    public String decrypt(String data) {
        return aes.decryptStr(data);
    }

}
