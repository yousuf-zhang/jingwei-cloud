package com.vastmoon.woodpecker.auth.service;

import com.vastmoon.sparrow.cache.config.CacheProperties;
import com.vastmoon.sparrow.cache.redis.FlexibleRedisProperties;
import com.vastmoon.sparrow.cache.redis.RedisItemConfigAware;
import com.vastmoon.woodpecker.auth.factory.ClientDetailsFactory;
import com.vastmoon.woodpecker.auth.service.repo.ClientDetailsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;

import java.time.Duration;

/**
 * <p> ClassName: ClientDetailsServiceImpl
 * <p> Description: 获取client信息
 *
 * @author yousuf 2020/11/26
 */
@Service("customClientDetails")
@CacheConfig(cacheNames = ClientDetailsServiceImpl.REDIS_CLIENT_CACHE,cacheManager = CacheProperties.REDIS_CACHE_MANAGER)
@RequiredArgsConstructor
public class ClientDetailsServiceImpl implements ClientDetailsService, RedisItemConfigAware {
    public final static String REDIS_CLIENT_CACHE = "oauth_client_details";
    private final static String REDIS_CLIENT_CACHE_PREFIX = "jingwei_cloud:oauth2:";
    private final ClientDetailsRepo clientDetailsRepo;

    @Override

    @Cacheable(key = "#clientId")
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return clientDetailsRepo.findClientByClientId(clientId)
                .map(ClientDetailsFactory::of)
                .orElseThrow(() -> new NoSuchClientException("No client with requested id: " + clientId));
    }

    @Override
    public FlexibleRedisProperties.RedisItemConfig redisProperties() {
        FlexibleRedisProperties.RedisItemConfig redisItemConfig = new FlexibleRedisProperties.RedisItemConfig(REDIS_CLIENT_CACHE);
        redisItemConfig.setKeyPrefix(REDIS_CLIENT_CACHE_PREFIX);
        redisItemConfig.setTimeToLive(Duration.ofMinutes(15));
        return redisItemConfig;
    }
}
