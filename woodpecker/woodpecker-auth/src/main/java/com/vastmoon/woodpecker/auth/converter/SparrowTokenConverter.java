package com.vastmoon.woodpecker.auth.converter;

import com.vastmoon.sparrow.client.dto.SuccessCode;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p> ClassName: SparrowTokenConverter
 * <p> Description: 定制化jwt token
 *
 * @author yousuf 2020/11/24
 */
public class SparrowTokenConverter extends DefaultUserAuthenticationConverter {
    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("sub", authentication.getName());
        if (CollectionUtils.isNotEmpty(authentication.getAuthorities())) {
            response.put(AUTHORITIES, AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        return response;
    }
}
