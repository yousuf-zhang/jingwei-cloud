package com.vastmoon.sparrow.security.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.vastmoon.sparrow.client.dto.RestCode;
import lombok.Getter;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import java.util.Objects;

/**
 * <p> ClassName: CustomOAuth2Exception
 * <p> Description: 自己封装异常 这里只是给出示例，感觉登录和权限异常没有必要封装
 *
 * @author yousuf 2020/11/5
 */
//@JsonSerialize(using = SparrowOAuthExceptionJacksonSerializer.class)
public class SparrowOAuth2Exception extends OAuth2Exception {
    private static final long serialVersionUID = 7694300897466570655L;
    @Getter
    protected RestCode code;

    protected Integer httpErrorCode;
    public SparrowOAuth2Exception(String msg, Throwable t) {
        super(msg, t);
    }



    public SparrowOAuth2Exception(String msg) {
        super(msg);
    }

    public SparrowOAuth2Exception(RestCode code) {
        super(code.message());
        this.code = code;
    }

    public SparrowOAuth2Exception(RestCode code, int httpErrorCode) {
        super(code.message());
        this.httpErrorCode = httpErrorCode;
    }

    @Override
    public String getOAuth2ErrorCode() {
        return Objects.isNull(code) ? super.getOAuth2ErrorCode() : code.code();
    }

    @Override
    public int getHttpErrorCode() {
        return Objects.isNull(httpErrorCode) ? super.getHttpErrorCode() : httpErrorCode;
    }
}
