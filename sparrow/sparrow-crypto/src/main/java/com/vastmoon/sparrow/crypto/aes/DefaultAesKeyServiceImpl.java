package com.vastmoon.sparrow.crypto.aes;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> ClassName: AesKeyServiceImpl
 * <p> Description: 默认实现 返回null
 *
 * @author yousuf 2020/2/26
 */
@Slf4j
public class DefaultAesKeyServiceImpl implements AesKeyService {
    @Override
    public AesKeyStore findDataEncryptionKey() {
        log.info("调用默认方法获取 AES KEY");
        return null;
    }
}
