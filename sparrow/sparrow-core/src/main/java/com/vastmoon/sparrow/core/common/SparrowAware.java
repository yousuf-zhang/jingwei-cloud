package com.vastmoon.sparrow.core.common;

/**
 * <p> ClassName: SparrowAware
 * <p> Description: 动态接口
 *
 * @author yousuf 2020/12/4
 */
public interface SparrowAware<T> {
    /**
     * Description: 动态绑定接口
     *
     * @return T
     *
     * @author yousuf 2020/12/4
     *
     */
    T instantiate();
}
