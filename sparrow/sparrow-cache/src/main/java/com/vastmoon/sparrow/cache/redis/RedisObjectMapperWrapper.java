package com.vastmoon.sparrow.cache.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * <p> ClassName: RedisObjectMapperWrapper
 * <p> Description: RedisObject包装类
 *
 * @author yousuf 2020/11/10
 */
public class RedisObjectMapperWrapper {
    public static final String BEAN_NAME = "redisMapperWrapper";
    protected final ObjectMapper mapper;
    private final Map<Class<?>, Class<?>> mixInMap;

    private RedisObjectMapperWrapper(ObjectMapper mapper) {
        this.mixInMap = Maps.newHashMap();
        this.mapper = mapper;
    }

    public static RedisObjectMapperWrapper wrapper() {
        return new RedisObjectMapperWrapper(new ObjectMapper());
    }

    protected void wrap() {}
    public RedisObjectMapperWrapper addMixIn(Class<?> target, Class<?> mixinSource) {
        mixInMap.put(target, mixinSource);
        return this;
    }
    public ObjectMapper build() {
        this.redisWrap();
        this.mixIn();
        this.wrap();
        return this.mapper;
    }

    private void mixIn() {
        mixInMap.forEach(this.mapper::addMixIn);
    }
    private void redisWrap() {
        mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.activateDefaultTyping(mapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
        // java8时间的时候回出错
        mapper.disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS);
        mapper.registerModule(new JavaTimeModule());
    }
}
