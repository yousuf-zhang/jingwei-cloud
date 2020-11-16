package com.vastmoon.sparrow.cache.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.vastmoon.sparrow.cache.caffeine.FlexibleCaffeineCacheManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> ClassName: CaffeineCacheAutoConfiguration
 * <p> Description: Caffeine 缓存配置
 *
 * @author yousuf 2020/11/10
 */
@Slf4j
@Configuration
@ConditionalOnClass(value = Caffeine.class)
@EnableConfigurationProperties(CacheProperties.class)
public class SparrowCaffeineCacheAutoConfiguration {
    @Bean(CacheProperties.CAFFEINE_CACHE_MANAGER)
    public FlexibleCaffeineCacheManager caffeineCacheManager(CacheProperties cacheProperties) throws Exception {
        log.info("Init FlexibleCaffeineCacheManager");
        return new FlexibleCaffeineCacheManager(cacheProperties.getCaffeine());
    }
}
