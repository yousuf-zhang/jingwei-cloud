package com.vastmoon.sparrow.core.exception;

import com.vastmoon.sparrow.client.dto.ParamError;
import com.vastmoon.sparrow.client.dto.RestCode;
import com.vastmoon.sparrow.core.exception.framework.FrameworkCode;

import java.util.Set;

/**
 * <p> ClassName: IllegalParamException
 * <p> Description: 参数校验异常
 *
 * @author yousuf 2020/11/6
 */
public class IllegalParamException extends BizException {
    private static final long serialVersionUID = -316527393455793007L;

    public IllegalParamException(String errorMessage) {
        super(errorMessage);
        super.setCode(FrameworkCode.ILLEGAL_PARAM_ERROR);
    }

    public IllegalParamException(String errorMessage, Throwable e) {
        super(errorMessage, e);
        super.setCode(FrameworkCode.ILLEGAL_PARAM_ERROR);
    }

    public IllegalParamException(RestCode code, String errMessage) {
        super(code, errMessage);
    }

    public IllegalParamException(String errorMessage, Set<ParamError> errors) {
        this(errorMessage);
        super.setErrors(errors);
    }

    public IllegalParamException(RestCode code, String errMessage, Set<ParamError> errors) {
        super(code, errMessage);
        super.setErrors(errors);
    }

    public IllegalParamException addError(String filed, String errorMessage) {
        super.setError(new ParamError(filed, errorMessage));
        return this;
    }
}
