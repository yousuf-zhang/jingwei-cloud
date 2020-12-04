package com.vastmoon.sparrow.cache.redis;

/**
 * <p> ClassName: RedisPropertiesAware
 * <p> Description: redis item config 回调接口，动态注入配置项
 *
 * @author yousuf 2020/12/1
 */
public interface RedisItemConfigAware {
    /**
     * Description: 配置信息
     *
     * @return com.vastmoon.sparrow.cache.redis.FlexibleRedisProperties.RedisItemConfig
     *
     * @author yousuf 2020/12/1
     *
     */
    FlexibleRedisProperties.RedisItemConfig redisProperties();
}
