package com.vastmoon.sparrow.core.domain;

import java.io.Serializable;

/**
 * <p> ClassName: Identity
 * <p> Description: 主键标识
 *
 * @author zhangshuai 2019/12/16
 */
public interface Identity<T> extends Serializable {
    /**
     * <p> Title: value
     * <p> Description: 获取主键值
     *
     * @return T
     *
     * @author zhangshuai 2019/12/16
     *
     */
    T value();
}
