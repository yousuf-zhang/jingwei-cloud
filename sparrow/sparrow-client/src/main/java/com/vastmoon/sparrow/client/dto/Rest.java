package com.vastmoon.sparrow.client.dto;

import java.util.Collection;

/**
 * <p> ClassName: Rest
 * <p> Description: rest接口
 *
 * @author yousuf 2020/11/6
 */
public interface Rest<T> extends BaseDTO {
    /**
     * 状态码 .
     *
     * @param code 业务码
     */
    void setCode(String code);

    /**
     * 数据载体.
     *
     * @param data the data
     */
    void setData(T data);

    /**
     * 提示信息.
     *
     * @param message the msg
     */
    void setMessage(String message);

    /**
     * Sets identifier.
     *
     * @param identifier 标识
     */
    void setIdentifier(String identifier);

    /**
     * Sets error.
     *
     * @param error 错误原因 一般用于参数校验
     */
    void setError(ParamError error);

    /**
     * Sets error.
     *
     * @param errors 错误原因 一般用于参数校验
     */
    void setErrors(Collection<ParamError> errors);
}
