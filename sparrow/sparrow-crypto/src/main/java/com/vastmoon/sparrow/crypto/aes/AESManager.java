package com.vastmoon.sparrow.crypto.aes;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import javax.crypto.SecretKey;
import java.util.*;

/**
 * <p> ClassName: AESManager
 * <p> Description: AES管理类
 *
 * @author yousuf 2020/12/4
 */
@Slf4j
@RequiredArgsConstructor
public class AESManager implements InitializingBean {
    private final Set<AesProperties> properties;
    private final AESKeyService aesKeyService;
    private Map<String, AESStore> aesHolder;
    @Override
    public void afterPropertiesSet() throws Exception {
        aesHolder = Maps.newHashMap();
        properties.forEach(aesProperties -> aesHolder.put(aesProperties.getName(),
                new AESStore(aesProperties.getName(), aesProperties.getKey())));
    }

    /**
     * Description: 获取aes的值
     *
     * @param name 名称
     *
     * @return java.util.Optional<com.vastmoon.sparrow.crypto.aes.AESStore>
     *
     * @author yousuf 2020/12/4
     *
     */
    public Optional<AESStore> get(String name) {
        AESStore aesStore = aesHolder.get(name);
        if (Objects.isNull(aesStore) && !aesHolder.containsKey(name)) {
            aesStore = aesKeyService.findAESKey(name);
            aesHolder.put(name, aesStore);
        }
        return Optional.ofNullable(aesStore);
    }

    public void register(AESStore aesStore) {
        if (aesHolder.containsKey(aesStore.getName())) {
            log.warn("该秘钥[{}]已经存在，现在覆盖秘钥", aesStore.getName());
        }
        aesHolder.put(aesStore.getName(), aesStore);
    }

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
    public static String generateKey(int keySize) {
        SecretKey publicKey = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(), keySize);
        return Base64.encode(publicKey.getEncoded());
    }
}
