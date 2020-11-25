package com.vastmoon.sparrow.crypto.aes;

/**
 * <p> ClassName: AesKeyService
 * <p> Description: 获取aes秘钥
 * 这个方法主要用于双重加密使用
 *
 * KEK 外层秘钥，一般为RSA提供
 * DEK 数据加密秘钥，一般通过数据库查询，然后通过KEK解密得到 DEK
 *
 * @author yousuf 2020/2/26
 */
public interface AesKeyService {
    /**bean name*/
    String AES_KEY_SERVICE_NAME = "aesKeyService";
    /**
     * <p> Title: findDataEncryptionKey
     * <p> Description: 获取数据加密秘钥
     *
     *
     * @return java.lang.String 加密的 DEK
     *
     * @author yousuf 2020/2/26
     *
     */
    AesKeyStore findDataEncryptionKey();
}
