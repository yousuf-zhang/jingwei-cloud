package com.vastmoon.sparrow.security.config;

import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * <p> ClassName: SparrowResourceServerAutoConfiguration
 * <p> Description: security 扫描类
 *
 * @author yousuf 2020/11/11
 */
@ConfigurationPropertiesScan
public class SparrowResourceServerAutoConfiguration {

    @Bean
    RemoteTokenServices remoteTokenServices() {
        RemoteTokenServices services = new RemoteTokenServices();
        services.setCheckTokenEndpointUrl("http://localhost:8101/oauth/check_token");
        services.setClientId("phoenix-user-center");
        services.setClientSecret("123456");
        return services;
    }
}
