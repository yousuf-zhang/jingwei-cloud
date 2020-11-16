package com.vastmoon.sparrow.client.dto;

/**
 * <p> ClassName: RestCode
 * <p> Description: 返回码接口
 *
 * @author yousuf 2020/11/5
 */
public interface RestCode {
    /**
     * Description: 状态码
     *
     * @return java.lang.String
     *
     * @author yousuf 2020/11/5
     *
     */
    String code();
    /**
     * Description: 描述
     *
     *
     * @return java.lang.String
     *
     * @author yousuf 2020/11/5
     *
     */
    String message();
}
