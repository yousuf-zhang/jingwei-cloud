package com.vastmoon.sparrow.cache.caffeine;

import com.google.common.collect.Maps;
import lombok.Data;

import java.util.Map;

/**
 * <p> ClassName: CaffeineProperties
 * <p> Description: Caffeine 配置类
 *
 * @author yousuf 2020/11/10
 */
@Data
public class CaffeineProperties {
    public static final String APP_BASE_CACHE = "caffeineCache";
    private boolean enableDefault = true;
    /**通用缓存*/
    private String jwtTokenSpec = "initialCapacity=50,maximumSize=5000,expireAfterAccess=15m";
    private Map<String, String> cacheSpecs = Maps.newHashMap();
}
