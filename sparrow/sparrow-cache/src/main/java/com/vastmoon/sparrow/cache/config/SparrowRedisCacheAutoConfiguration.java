package com.vastmoon.sparrow.cache.config;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.vastmoon.sparrow.cache.redis.FlexibleRedisProperties;
import com.vastmoon.sparrow.cache.redis.RedisItemConfigAware;
import com.vastmoon.sparrow.cache.redis.RedisManager;
import com.vastmoon.sparrow.cache.redis.RedisObjectMapperWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p> ClassName: RedisCacheAutoConfiguration
 * <p> Description: redis缓存配置类
 *
 * @author yousuf 2020/11/10
 */
@Slf4j
@Configuration
@ConditionalOnClass(value = RedisCacheManager.class)
@EnableConfigurationProperties(CacheProperties.class)
public class SparrowRedisCacheAutoConfiguration extends CachingConfigurerSupport implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Bean(RedisObjectMapperWrapper.BEAN_NAME)
    @ConditionalOnMissingBean(name = RedisObjectMapperWrapper.BEAN_NAME)
    public RedisObjectMapperWrapper redisObjectMapperWrapper() {
        return RedisObjectMapperWrapper.wrapper();
    }

    @Bean
    public RedisSerializer<Object> jackson2JsonRedisSerializer(RedisObjectMapperWrapper redisMapperWrapper) {
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        serializer.setObjectMapper(redisMapperWrapper.build());
        return serializer;
    }

    @Bean(name = "redisTemplate")
    @ConditionalOnClass(RedisOperations.class)
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory,
                                                       RedisObjectMapperWrapper redisMapperWrapper) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setValueSerializer(jackson2JsonRedisSerializer(redisMapperWrapper));
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(jackson2JsonRedisSerializer(redisMapperWrapper));
        template.afterPropertiesSet();
        return template;
    }

    @Primary
    @Bean(CacheProperties.REDIS_CACHE_MANAGER)
    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory,
                                          CacheProperties cacheProperties,
                                          RedisObjectMapperWrapper redisMapperWrapper) {
        // 生成一个默认配置，通过config对象即可对缓存进行自定义配置
        Map<String, RedisCacheConfiguration> redisMap = Maps.newHashMap();
        postRedisItemConfigs(cacheProperties.getRedis());
        List<FlexibleRedisProperties.RedisItemConfig> properties = cacheProperties.getRedis().getConfigs();
        if(CollectionUtils.isEmpty(properties)) {
            properties = FlexibleRedisProperties.defaultCache().getConfigs();
        }
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(new StringRedisSerializer()))
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(jackson2JsonRedisSerializer(redisMapperWrapper)));
        for (FlexibleRedisProperties.RedisItemConfig pro : properties) {
            config = config.entryTtl(pro.getTimeToLive());
            if (pro.isUseKeyPrefix()) {
                config = config.computePrefixWith(CacheKeyPrefix.prefixed(pro.getKeyPrefix()));
            } else {
                config = config.disableKeyPrefix();
            }
            if (!pro.isCacheNullValues()) {
                config = config.disableCachingNullValues();
            }
            redisMap.put(pro.getCacheName(), config);
        }
        log.info("init redisCacheManager");
        return RedisCacheManager.builder(redisConnectionFactory)
                // 一定要先调用该方法设置初始化的缓存名，再初始化相关的配置
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig())
                .initialCacheNames(redisMap.keySet())
                .withInitialCacheConfigurations(redisMap)
                .build();
    }

    @Bean
    @ConditionalOnBean(name = "redisTemplate")
    public RedisManager redisManager(RedisTemplate<String, Object> redisTemplate) {
        return new RedisManager(redisTemplate);
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    /**
     * Description: 支持通过接口配置redis缓存
     *
     * @param redisProperties redis配置项
     *
     * @author yousuf 2020/12/1
     *
     */
    private void postRedisItemConfigs(FlexibleRedisProperties redisProperties) {
        Map<String, FlexibleRedisProperties.RedisItemConfig> propertiesMap = redisProperties.getConfigs().stream().
                collect(Collectors.toMap(FlexibleRedisProperties.RedisItemConfig::getCacheName,
                        redisItemConfig -> redisItemConfig));
        Map<String, RedisItemConfigAware> redisItemConfigAwareMap = applicationContext.getBeansOfType(RedisItemConfigAware.class);
        redisItemConfigAwareMap.forEach((key, redisItemConfigAware) -> {
            String cacheName = redisItemConfigAware.redisProperties().getCacheName();
            if (Objects.isNull(propertiesMap.get(cacheName))) {
                redisProperties.addItem(redisItemConfigAware.redisProperties());
            }
        });
    }
}
