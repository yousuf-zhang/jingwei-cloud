package com.vastmoon.sparrow.core.domain;


/**
 * <p> ClassName: Aggregate
 * <p> Description: 聚合 一般命名为： 名词 + aggr
 *
 * @author zhangshuai 2019/12/6
 */
public interface AggregateRoot<R extends AbstractEntity<E, V>, E extends Identity<V>, V> {
    /**
     * <p> Title: root
     * <p> Description: 获得聚合跟
     *
     * @return R 聚合根
     *
     * @author zhangshuai 2019/12/6
     *
     */
    R root();
}
