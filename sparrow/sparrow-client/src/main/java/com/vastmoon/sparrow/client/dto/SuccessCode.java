package com.vastmoon.sparrow.client.dto;

/**
 * <p> ClassName: SuccessCode
 * <p> Description: 成功信息返回
 *
 * @author yousuf 2020/11/5
 */
public enum SuccessCode implements RestCode {
    /**成功*/
    SUCCESS("SUCCESS", "Success"),
    ;
    private final String code;
    private final String message;

    SuccessCode(String code, String message) {
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
