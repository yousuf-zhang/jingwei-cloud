package com.vastmoon.sparrow.core.exception.framework;

import com.vastmoon.sparrow.client.dto.RestCode;

/**
 * <p> ClassName: FrameworkException
 * <p> Description: 框架异常 最顶层异常
 *
 * @author yousuf 2020/11/6
 */
public class FrameworkException extends BaseException {
    private static final long serialVersionUID = -2476020090340575066L;
    public FrameworkException(String errorMessage) {
        super(errorMessage);
        this.setCode(FrameworkCode.FRAMEWORK_ERROR);
    }

    public FrameworkException(String errorMessage, Throwable e) {
        super(errorMessage, e);
        this.setCode(FrameworkCode.FRAMEWORK_ERROR);
    }

    public FrameworkException(RestCode code, String errorMessage) {
        super(errorMessage);
        this.setCode(code);
    }
}
