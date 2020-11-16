package com.vastmoon.sparrow.core.exception.framework;


import com.vastmoon.sparrow.client.dto.RestCode;

/**
 * <p> ClassName: FrameworkCode
 * <p> Description: 框架错误代码
 *
 * @author yousuf 2020/11/6
 */
public enum FrameworkCode implements RestCode {
    /**全局状态码*/
    FRAMEWORK_ERROR("FRAMEWORK_ERROR", "Framework error"),
    BIZ_ERROR("BIZ_ERROR", "General business logic error"),
    SYS_ERROR("SYS_ERROR" , "Unknown system error" ),
    ILLEGAL_PARAM_ERROR("ILLEGAL_PARAM_ERROR", "Request parameter exception"),
    METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "Request method error"),
    JSON_CONVERT_ERROR("JSON_CONVERT_ERROR", "json format error"),
    NOT_FOUND("NOT_FOUND", "not found")
    ;
    private final String code;
    private final String message;

    FrameworkCode(String code, String message) {
        this.code = code;
        this.message = message;
    }


    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
