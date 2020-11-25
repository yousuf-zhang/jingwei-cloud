package com.vastmoon.woodpeker.auth.handler;

import com.vastmoon.sparrow.core.handler.BaseExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * <p> ClassName: GlobalExceptionHandler
 * <p> Description: 异常统一处理
 *
 * @author yousuf 2020/11/25
 */
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends BaseExceptionHandler {
}
