package com.vastmoon.sparrow.cache.redis;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.List;

/**
 * <p> ClassName: FlexibleRedisProperties
 * <p> Description: redis配置项
 *
 * @author yousuf 2020/11/10
 */
@Data
public class FlexibleRedisProperties {
    private static final String DEFAULT_CACHE_NAME ="redisCache";
    private List<RedisItemConfig> configs = Lists.newArrayList();

    @Data
    public static class RedisItemConfig {
        @NotNull
        private String cacheName;
        private Duration timeToLive = Duration.ZERO;
        private boolean cacheNullValues = true;
        private String keyPrefix="";
        private boolean useKeyPrefix = true;
        public RedisItemConfig() {
        }
        public RedisItemConfig(String cacheName) {
            this.cacheName = cacheName;
        }
    }

    public static FlexibleRedisProperties defaultCache() {
        return new FlexibleRedisProperties().addItem(new RedisItemConfig(DEFAULT_CACHE_NAME));
    }

    public FlexibleRedisProperties addItem(RedisItemConfig item) {
        this.configs.add(item);
        return this;
    }
}
