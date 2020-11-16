package com.vastmoon.sparrow.core.exception;

import com.vastmoon.sparrow.client.dto.RestCode;
import com.vastmoon.sparrow.core.exception.framework.BaseException;
import com.vastmoon.sparrow.core.exception.framework.FrameworkCode;

/**
 * <p> ClassName: BizException
 * <p> Description: 业务异常
 *
 * @author yousuf 2020/11/6
 */
public class BizException extends BaseException {
    private static final long serialVersionUID = 7098246204620459200L;

    public BizException(String errorMessage) {
        super(errorMessage);
        this.setCode(FrameworkCode.BIZ_ERROR);
    }

    public BizException(String errorMessage, Throwable e) {
        super(errorMessage, e);
        this.setCode(FrameworkCode.BIZ_ERROR);
    }

    public BizException(RestCode code, String errMessage) {
        super(errMessage);
        this.setCode(code);
    }
}
