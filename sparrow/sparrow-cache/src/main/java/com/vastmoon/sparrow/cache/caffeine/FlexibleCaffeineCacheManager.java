package com.vastmoon.sparrow.cache.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.Maps;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cache.caffeine.CaffeineCacheManager;

import java.util.Map;

/**
 * <p> ClassName: FlexibleCaffeineCacheManager
 * <p> Description: Caffeine 缓存改造
 *
 * @author yousuf 2020/11/10
 */
@Data
@SuppressWarnings({"rawtypes", "RedundantThrows"})
public class FlexibleCaffeineCacheManager extends CaffeineCacheManager implements InitializingBean {
    private final CaffeineProperties properties;
    private Map<String, Caffeine<Object, Object>> builders = Maps.newHashMap();
    private CacheLoader cacheLoader;

    public FlexibleCaffeineCacheManager(CaffeineProperties properties) throws Exception {
        super();
        this.properties = properties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (properties.isEnableDefault()) {
            builders.put(CaffeineProperties.APP_BASE_CACHE,
                    Caffeine.from(properties.getJwtTokenSpec()));
        }
        this.properties.getCacheSpecs().forEach((key, value) -> builders.put(key, Caffeine.from(value)));
    }

    @Override
    @SuppressWarnings({"unchecked", "NullableProblems"})
    protected Cache<Object, Object> createNativeCaffeineCache(String name) {
        Caffeine<Object, Object> builder = builders.get(name);
        if (builder == null) {
            return super.createNativeCaffeineCache(name);
        }

        if (this.cacheLoader != null) {
            return builder.build(this.cacheLoader);
        } else {
            return builder.build();
        }
    }


    @Override
    @SuppressWarnings({"unchecked", "NullableProblems"})
    public void setCacheLoader(CacheLoader cacheLoader) {
        super.setCacheLoader(cacheLoader);
        this.cacheLoader = cacheLoader;
    }
}
