package com.vastmoon.woodpecker.auth.factory;

import com.vastmoon.woodpecker.auth.po.ClientDetailsPO;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * <p> ClassName: ClientDetailsMapper
 * <p> Description: 转换
 *
 * @author yousuf 2020/11/27
 */
@Slf4j
@UtilityClass
public class ClientDetailsFactory {
    /**
     * Description: 转换为 BaseClientDetails
     *
     * @param clientDetails po
     *
     * @return org.springframework.security.oauth2.provider.client.BaseClientDetails
     *
     * @author yousuf 2020/11/27
     *
     */
    public BaseClientDetails of(ClientDetailsPO clientDetails) {
        BaseClientDetails details = new BaseClientDetails(clientDetails.getClientId(),
                clientDetails.getResourceIds(), clientDetails.getScope(), clientDetails.getAuthorizedGrantTypes(),
                clientDetails.getAuthorities(), clientDetails.getRedirectUris());
        details.setClientSecret(clientDetails.getClientSecret());
        details.setAccessTokenValiditySeconds(clientDetails.getAccessTokenValiditySeconds());
        details.setRefreshTokenValiditySeconds(clientDetails.getRefreshTokenValiditySeconds());
        if (!StringUtils.isEmpty(clientDetails.getScope())) {
            details.setAutoApproveScopes(StringUtils.commaDelimitedListToSet(clientDetails.getScope()));
        }
        String json = clientDetails.getAdditionalInformation();
        if (!StringUtils.isEmpty(json)) {
            try {
                @SuppressWarnings("unchecked")
                Map<String, Object> additionalInformation = ClientDetailsJsonMapper.read(json, Map.class);
                details.setAdditionalInformation(additionalInformation);
            }
            catch (Exception e) {
                log.warn("Could not decode JSON for additional information: " + details, e);
            }
        }
        return details;
    };
}
