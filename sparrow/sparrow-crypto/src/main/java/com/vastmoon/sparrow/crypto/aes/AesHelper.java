package com.vastmoon.sparrow.crypto.aes;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.vastmoon.sparrow.core.common.AbstractSparrowContext;
import com.vastmoon.sparrow.core.enumeration.TrueOrFalseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.SecretKey;
import java.util.Objects;

/**
 * <p> ClassName: AesHelper
 * <p> Description: AES加密
 *
 * @author yousuf 2020/2/26
 */
@AllArgsConstructor
public class AesHelper extends AbstractSparrowContext {
    private static int KEY_SIZE;
    private static AES aes;
    @Getter
    private static AesKeyStore keyStore;
    private final AesProperties properties;
    private final AesKeyService aesKeyService;

    /**
     * <p> Title: generateKey
     * <p> Description: 生成秘钥
     *
     *
     * @return java.lang.String
     *
     * @author yousuf 2020/2/27
     *
     */
    public static String generateKey() {
        SecretKey publicKey = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(), KEY_SIZE);
        return Base64.encode(publicKey.getEncoded());
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
    public static String encrypt(String data) {
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
    public static String decrypt(String data) {
        return aes.decryptStr(data);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        KEY_SIZE = properties.getKeySize();
        keyStore = aesKeyService.findDataEncryptionKey();
        if (Objects.isNull(keyStore)) {
            String key = properties.getKey();
            if (StringUtils.isNotBlank(key)) {
                keyStore = AesKeyStore.builder()
                        .aesKey(key)
                        .name("default")
                        .enable(TrueOrFalseEnum.TRUE)
                        .build();
            }

        }
        aes = Objects.isNull(keyStore) ? SecureUtil.aes() : SecureUtil.aes(Base64.decode(keyStore.getAesKey()));
    }

}
