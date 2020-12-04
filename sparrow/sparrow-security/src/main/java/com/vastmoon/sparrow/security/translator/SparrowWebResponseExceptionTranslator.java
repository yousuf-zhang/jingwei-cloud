package com.vastmoon.sparrow.security.translator;

import com.vastmoon.sparrow.security.exception.SparrowOAuth2Exception;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;

/**
 * <p> ClassName: SparrowWebResponseExceptionTranslator
 * <p> Description: 自定义OAuth2异常转换类
 *
 * @author yousuf 2020/11/30
 */
public class SparrowWebResponseExceptionTranslator extends DefaultWebResponseExceptionTranslator {
    @Override
    public ResponseEntity<OAuth2Exception> translate(Exception e) throws Exception {
        ResponseEntity<OAuth2Exception> responseEntity = super.translate(e);
        SparrowOAuth2Exception exception = new SparrowOAuth2Exception(e.getMessage(), e);
        return new ResponseEntity<>(exception, responseEntity.getHeaders(), responseEntity.getStatusCode());
    }
}
