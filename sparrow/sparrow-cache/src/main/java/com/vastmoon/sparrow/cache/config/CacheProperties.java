package com.vastmoon.sparrow.cache.config;


import com.vastmoon.sparrow.cache.caffeine.CaffeineProperties;
import com.vastmoon.sparrow.cache.redis.FlexibleRedisProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * <p> ClassName: CacheProperties
 * <p> Description: 缓存配置类
 *
 * @author yousuf 2020/11/10
 */
@Data
@ConfigurationProperties(prefix = "vastmoon.cache")
public class CacheProperties {
    public static final String CAFFEINE_CACHE_MANAGER = "caffeineCacheManager";
    public static final String REDIS_CACHE_MANAGER = "redisCacheManager";
    @NestedConfigurationProperty
    private CaffeineProperties caffeine = new CaffeineProperties();
    @NestedConfigurationProperty
    private FlexibleRedisProperties redis = new FlexibleRedisProperties();
}
