package com.vastmoon.woodpeker.auth.config;

import com.vastmoon.woodpeker.auth.converter.SparrowTokenConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.security.KeyPair;

/**
 * <p> ClassName: AuthorizationServerConfig
 * <p> Description: 授权服务器
 *
 * @author yousuf 2020/11/24
 */
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final KeyPair keyPair;
    private final UserDetailsService userDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
            throws Exception {
        // @formatter:off
        clients.inMemory()
                .withClient("reader")
                .authorizedGrantTypes("password", "refresh_token")
                .secret("{noop}secret")
                .scopes("message:read", "refresh_token")
                .accessTokenValiditySeconds(600_000_000)
                .and()
                .withClient("writer")
                .authorizedGrantTypes("password", "refresh_token")
                .secret("{noop}secret")
                .scopes("message:write", "refresh_token")
                .accessTokenValiditySeconds(600_000_000)
                .and()
                .withClient("noscopes")
                .authorizedGrantTypes("password")
                .secret("{noop}secret")
                .scopes("none")
                .accessTokenValiditySeconds(600_000_000);
        // @formatter:on
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(this.authenticationManager)
                .tokenStore(tokenStore())
                .userDetailsService(userDetailsService)
                .accessTokenConverter(accessTokenConverter());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.allowFormAuthenticationForClients().checkTokenAccess("permitAll()");
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setKeyPair(this.keyPair);
        DefaultAccessTokenConverter accessTokenConverter = new DefaultAccessTokenConverter();
        accessTokenConverter.setUserTokenConverter(new SparrowTokenConverter());
        converter.setAccessTokenConverter(accessTokenConverter);
        return converter;
    }

}
