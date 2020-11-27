package com.vastmoon.sparrow.core.domain;



/**
 * <p> ClassName: AbstractEntity
 * <p> Description: 实体类
 *
 * @author zhangshuai 2019/12/16
 */
public abstract class AbstractEntity<ID extends Identity<V>, V> implements Entity {
    private static final long serialVersionUID = 2798611779975537124L;

    /**
     * <p> Title: id
     * <p> Description: 获取主键
     *
     * @return ID
     *
     * @author zhangshuai 2019/12/17
     *
     */
    public abstract ID id();
}
