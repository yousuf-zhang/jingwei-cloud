package com.vastmoon.sparrow.core.util;

import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * <p> ClassName: WebUtils
 * <p> Description: 工具类
 *
 * @author yousuf 2020/11/6
 */
@UtilityClass
public class WebUtils extends org.springframework.web.util.WebUtils {
    /**IP头文件*/
    private static final String[] IP_HEADERS = {
            "X-Forwarded-For",
            "X-Real-IP",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP"
    };
    /**
     * Description: 获取request
     *
     *
     * @return javax.servlet.http.HttpServletRequest
     *
     * @author yousuf 2020/11/6
     *
     */
    public HttpServletRequest getRequest() {
        ServletRequestAttributes attrs =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        RequestContextHolder.setRequestAttributes(attrs, true);
        return Objects.requireNonNull(attrs).getRequest();
    }

    /**
     * Description: 返回json
     *
     * @param response response
     * @param json json字符串
     * @param status 状态
     *
     * @author yousuf 2020/11/6
     *
     */
    public void responseJsonWriter(HttpServletResponse response, String json, int status) throws IOException {
        response.setStatus(status);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().write(json.getBytes());
    }

    public String getIpAddress() {
        for (String ipHeader : IP_HEADERS) {
            String ip = getRequest().getHeader(ipHeader);
            if (!StringUtils.isEmpty(ip) && !ip.contains("unknown")) {
                return ip;
            }
        }
        return getRequest().getRemoteAddr();
    }

    public String requestFullUrl() {
        return requestFullUrl(getRequest());
    }

    public String requestFullUrl(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getRequestURI();
    }
}
