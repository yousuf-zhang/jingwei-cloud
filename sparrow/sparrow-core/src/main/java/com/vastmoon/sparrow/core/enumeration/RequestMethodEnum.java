package com.vastmoon.sparrow.core.enumeration;

/**
 * <p> ClassName: RequestMethodEnum
 * <p> Description: 请求方式枚举
 *
 * @author yousuf 2020/3/2
 */
public enum RequestMethodEnum implements Enumerator<Integer> {
    /**http请求方式枚举*/
    NONE(0, "N/A"),
    GET(1, "GET"),
    POST(2, "POST"),
    PUT(3, "PUT"),
    PATCH(4, "PATCH"),
    DELETE(5, "DELETE"),
    HEAD(6, "HEAD"),
    OPTIONS(7, "OPTIONS"),
    TRACE(8, "TRACE"),
    CONNECT(9, "CONNECT"),
    ALL(10, "ALL")
    ;
    private final Integer code;
    private final String text;

    RequestMethodEnum(Integer code, String text) {
        this.code = code;
        this.text = text;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getText() {
        return this.text;
    }

    /**
     * <p> Title: of
     * <p> Description: text 转换为 Enum
     *
     * @param method 方法
     *
     * @return com.vastmoon.com.vastmoon.sparrow.enums.RequestMethodEnum
     *
     * @author yousuf 2020/3/3
     *
     */
    public static RequestMethodEnum of(String method) {
        return Enumerator.findByCode(RequestMethodEnum.class, method);
    }
}
