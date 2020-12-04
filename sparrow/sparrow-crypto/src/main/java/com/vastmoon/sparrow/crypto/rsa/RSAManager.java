package com.vastmoon.sparrow.crypto.rsa;

import com.google.common.collect.Maps;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * <p> ClassName: RasManager
 * <p> Description: RSA 管理类，管理应用中的RSA秘钥
 *
 * @author yousuf 2020/12/4
 */
@Slf4j
@RequiredArgsConstructor
public class RSAManager implements InitializingBean {
    private final Set<RSAProperties> properties;
    private Map<String, RSAStore> rsaHolder;
    @Override
    public void afterPropertiesSet() throws Exception {
        rsaHolder = Maps.newHashMap();
       properties.forEach(rsaProperties -> rsaHolder.put(rsaProperties.getName(), new RSAStore(rsaProperties)));
    }

    public Optional<RSAStore> get(String name) {
        return Optional.ofNullable(rsaHolder.get(name));
    }

    public void register(RSAStore rsaStore) {
        if (rsaHolder.containsKey(rsaStore.getName())) {
            log.warn("该秘钥[{}]已经存在，现在覆盖秘钥", rsaStore.getName());
        }
        rsaHolder.put(rsaStore.getName(), rsaStore);
    }

}
