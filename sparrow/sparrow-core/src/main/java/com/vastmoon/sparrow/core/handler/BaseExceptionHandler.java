package com.vastmoon.sparrow.core.handler;


import com.vastmoon.sparrow.client.dto.ParamError;
import com.vastmoon.sparrow.client.dto.Rest;
import com.vastmoon.sparrow.client.dto.RestBody;
import com.vastmoon.sparrow.core.exception.BizException;
import com.vastmoon.sparrow.core.exception.IllegalParamException;
import com.vastmoon.sparrow.core.exception.framework.BaseException;
import com.vastmoon.sparrow.core.exception.framework.FrameworkCode;
import com.vastmoon.sparrow.core.util.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * <p> ClassName: BaseExceptionHandler
 * <p> Description: 系统异常
 *
 * @author yousuf 2020/11/6
 */
@Slf4j
public abstract class BaseExceptionHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Rest<ParamError> paramError(HttpServletRequest request, MethodArgumentNotValidException ex) {
        ParamError error = ex.getBindingResult().getFieldErrors()
                .stream().findFirst().map(fieldError -> new ParamError(fieldError.getField(), fieldError.getDefaultMessage()))
                .orElse(null);
        // 参数异常返回400
        log.warn("传入参数不合法, url({}), method({}), exception: {}",
                WebUtils.requestFullUrl(request),
                request.getMethod(),
                ExceptionUtils.getMessage(ex));
        return RestBody.failure(FrameworkCode.ILLEGAL_PARAM_ERROR, error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Rest<ParamError> paramError(HttpServletRequest request, ConstraintViolationException ex) {
        ParamError error = ex.getConstraintViolations()
                .stream().findFirst().map(constraintViolation ->
                        new ParamError(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage()))
                .orElse(null);
        // 参数异常返回400
        log.warn("传入参数不合法, url({}), method({}), exception: {}",
                WebUtils.requestFullUrl(request),
                request.getMethod(),
                ExceptionUtils.getMessage(ex));
        return RestBody.failure(FrameworkCode.ILLEGAL_PARAM_ERROR, error);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalParamException.class)
    public Rest<ParamError> paramError(HttpServletRequest request, IllegalParamException ex) {
        // 参数异常返回400
        log.warn("传入参数不合法, code({}), message({}), url({}), method({}), exception: {}",
                ex.getCode(),
                ex.getMessage(),
                WebUtils.requestFullUrl(request),
                request.getMethod(),
                ExceptionUtils.getMessage(ex));
        return RestBody.failure(ex.getCode(), ex.getError());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BizException.class)
    public Rest<Void> bizError(HttpServletRequest request, BaseException ex) {
        // 业务逻辑异常返回 400
        log.warn("业务异常, code({}), message({}), url({}), method({}), exception: {}",
                ex.getCode(),
                ex.getMessage(),
                WebUtils.requestFullUrl(request),
                request.getMethod(),
                ExceptionUtils.getMessage(ex));
        return RestBody.failure(ex.getCode());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = BaseException.class)
    public Rest<Void> sparrowError(HttpServletRequest request, BaseException ex) {
        log.warn("系统异常, code({}), message({}), url({}), method({}), exception: {}",
                ex.getCode(),
                ex.getMessage(),
                WebUtils.requestFullUrl(request),
                request.getMethod(),
                ExceptionUtils.getMessage(ex));
        return RestBody.failure(ex.getCode());
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public Rest<Void> sparrowError(HttpServletRequest request, HttpRequestMethodNotSupportedException ex) {
        log.warn("请求方式错误, url({}), method({}), exception: {}",
                WebUtils.requestFullUrl(request),
                request.getMethod(),
                ExceptionUtils.getMessage(ex));
        return RestBody.failure(FrameworkCode.METHOD_NOT_ALLOWED);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public Rest<Void> jsonConvertError(HttpServletRequest request, HttpMessageNotReadableException ex) {
        log.warn("请求格式错误, url({}), method({}), exception: {}",
                WebUtils.requestFullUrl(request),
                request.getMethod(), ExceptionUtils.getMessage(ex));
        return RestBody.failure(FrameworkCode.JSON_CONVERT_ERROR);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = Exception.class)
    public Rest<Void> error(HttpServletRequest request, Exception ex) {
        log.error("未捕获的异常, url({}), method({}), exception: {}",
                request.getServletPath(),
                request.getMethod(), ExceptionUtils.getStackTrace(ex));
        return RestBody.failure(FrameworkCode.SYS_ERROR);
    }
}
