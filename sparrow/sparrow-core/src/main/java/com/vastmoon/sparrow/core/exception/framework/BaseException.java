package com.vastmoon.sparrow.core.exception.framework;

import com.google.common.collect.Sets;
import com.vastmoon.sparrow.client.dto.ParamError;
import com.vastmoon.sparrow.client.dto.RestCode;
import lombok.Data;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Set;

/**
 * <p> ClassName: BaseException
 * <p> Description: 基础异常
 *
 * @author yousuf 2020/11/6
 */
@Data
public class BaseException extends RuntimeException{
    private static final long serialVersionUID = -7195704960585463155L;
    private RestCode code;
    private ParamError error;
    Set<ParamError> errors = Sets.newHashSet();

    public BaseException(String errorMessage){
        super(errorMessage);
    }

    public BaseException(String errorMessage, Throwable e) {
        super(errorMessage, e);
    }

    public BaseException(String message, Set<ParamError> errors) {
        super(message);
        if (CollectionUtils.isEmpty(errors)) {
            errors = Sets.newHashSet();
        }
        this.errors = errors;
    }
}
