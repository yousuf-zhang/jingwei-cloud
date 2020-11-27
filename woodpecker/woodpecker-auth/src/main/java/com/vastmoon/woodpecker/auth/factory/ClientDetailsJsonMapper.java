package com.vastmoon.woodpecker.auth.factory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

/**
 * <p> ClassName: ClientDetailsJsonMapper
 * <p> Description: 转换类
 *
 * @author yousuf 2020/11/27
 */
@Component
@RequiredArgsConstructor
public class ClientDetailsJsonMapper implements InitializingBean {
    private static ObjectMapper jsonMapper;
    private final ObjectMapper objectMapper;
    @Override
    public void afterPropertiesSet() throws Exception {
        jsonMapper = objectMapper;
    }

    @SneakyThrows
    public static void write(Object input) {
        jsonMapper.writeValueAsString(input);
    }

    @SneakyThrows
    public static <T> T read(String input, Class<T> type) {
        return jsonMapper.readValue(input, type);
    }
}
