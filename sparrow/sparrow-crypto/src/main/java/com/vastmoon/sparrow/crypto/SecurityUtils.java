package com.vastmoon.sparrow.crypto;

import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;

/**
 * <p> ClassName: SecurityUtils
 * <p> Description: 其他辅助算法
 *
 * @author yousuf 2020/2/27
 */
public class SecurityUtils {
    /**
     * <p> Title: sha256
     * <p> Description: 生成sha256字符串
     *
     * @param data 数据
     *
     * @return java.lang.String sha256
     *
     * @author yousuf 2020/2/27
     *
     */
    public static String sha256(String data) {
        return SecureUtil.sha256(data);
    }
    /**
     * <p> Title: sha256WithSalt
     * <p> Description: sha256加盐算法
     *
     * @param data 数据
     * @param salt 盐值
     *
     * @return java.lang.String
     *
     * @author yousuf 2020/2/27 
     *
     */
    public static String sha256WithSalt(String data, String salt) {
        return sha256(data + salt);
    }

    /**
     * <p> Title: simpleUUID
     * <p> Description: 去掉中划线的uuid
     *
     * @see IdUtil#fastSimpleUUID()
     *
     * @return java.lang.String
     *
     * @author yousuf 2020/2/27
     *
     */
    public static String simpleUUID() {
        return IdUtil.fastSimpleUUID();
    }

    /**
     * <p> Title: randomUUID
     * <p> Description: 获取UUID
     *
     * @see IdUtil#fastUUID()
     *
     * @return java.lang.String
     *
     * @author yousuf 2020/2/27
     *
     */
    public static String randomUUID() {
        return IdUtil.fastUUID();
    }

    /**
     * <p> Title: objectId
     * <p> Description: 生成objectID
     *
     * @see IdUtil#objectId()
     *
     * @return java.lang.String
     *
     * @author yousuf 2020/2/27
     *
     */
    public static String objectId() {
        return ObjectId.next();
    }
}
