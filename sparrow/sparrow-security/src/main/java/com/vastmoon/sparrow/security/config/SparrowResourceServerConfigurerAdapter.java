package com.vastmoon.sparrow.security.config;

import com.vastmoon.sparrow.security.translator.SparrowWebResponseExceptionTranslator;
import org.springframework.core.Ordered;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * <p> ClassName: SparrowResourceServerAutoConfiguration
 * <p> Description: 配置类
 *
 * @author yousuf 2020/11/27
 */
public class SparrowResourceServerConfigurerAdapter extends WebSecurityConfigurerAdapter implements Ordered {
    public static final String RESOURCE_SERVER_CONFIGURER = "resourceServerConfigurerAdapter";
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(auth -> auth.mvcMatchers("/**").authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .cors(withDefaults())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .oauth2ResourceServer(oauth -> {
                    OAuth2AuthenticationEntryPoint authenticationEntryPoint = new OAuth2AuthenticationEntryPoint();
//                    authenticationEntryPoint.setExceptionTranslator(new SparrowWebResponseExceptionTranslator());
                    oauth.jwt();
                    oauth.accessDeniedHandler(new OAuth2AccessDeniedHandler())
                            .authenticationEntryPoint(authenticationEntryPoint);
                });
    }

    @Override
    public int getOrder() {
        return 90;
    }
}
