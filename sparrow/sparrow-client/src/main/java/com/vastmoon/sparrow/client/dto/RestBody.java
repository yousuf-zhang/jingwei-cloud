package com.vastmoon.sparrow.client.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * <p> ClassName: Rest
 * <p> Description: 返回信息封装
 *
 * @author yousuf 2020/11/5
 */
@Data
@NoArgsConstructor
public class RestBody<T> implements Rest<T> {
    private static final long serialVersionUID = 3450363750233658205L;
    private String code;
    private String message;
    private String identifier;
    private T data;
    private Collection<ParamError> errors;
    private ParamError error;

    public RestBody(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public RestBody(RestCode code) {
        this(code.code(), code.message());
    }
    public static <T> RestBody<T> success(T data) {
        RestBody<T> response = success();
        response.setData(data);
        return response;
    }
    public static <T> RestBody<T> success() {
        return new RestBody<>(SuccessCode.SUCCESS);
    }
    public static <T> RestBody<T> failure(String code, String message) {
        return new RestBody<>(code, message);
    }

    public static <T> RestBody<T> failure(RestCode code) {
        return new RestBody<>(code.code(), code.message());
    }

    public static <T> RestBody<T> failure(RestCode code, Collection<ParamError> errors) {
        RestBody<T> response = failure(code);
        response.setErrors(errors);
        return response;
    }

    public static <T> RestBody<T> failure(RestCode code, ParamError error) {
        RestBody<T> response = failure(code);
        response.setError(error);
        return response;
    }
}
