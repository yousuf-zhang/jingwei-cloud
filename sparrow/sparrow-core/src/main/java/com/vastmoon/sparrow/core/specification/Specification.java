package com.vastmoon.sparrow.core.specification;

/**
 * <p> ClassName: Specification
 * <p> Description: 规约模式-校验通用入口
 *
 * @author zhangshuai 2019/12/12
 */
public interface Specification<T> {
    /**
     * <p> Title: isSatisfiedBy
     * <p> Description: 规则验证接口
     *
     * @param candidate 数据
     *
     * @return boolean
     *
     * @author zhangshuai 2019/12/12
     *
     */
    default boolean isSatisfiedBy(T candidate){return true;}
    /**
     * <p> Title: and
     * <p> Description: 并且关系 &&
     *
     * @param specification 规则
     *
     * @return com.vastmoon.pay.common.specification.Specification<T>
     *
     * @author zhangshuai 2019/12/12
     *
     */
    Specification<T> and(Specification<T> specification);
    /**
     * <p> Title: or
     * <p> Description: 或关系 ||
     *
     * @param specification 规则
     *
     * @return com.vastmoon.pay.common.specification.Specification<T>
     *
     * @author zhangshuai 2019/12/12
     *
     */
    Specification<T> or(Specification<T> specification);

    /**
     * <p> Title: not
     * <p> Description: 不等于关系 ！=
     *
     * @param specification 规则
     *
     * @return com.vastmoon.pay.common.specification.Specification<T>
     *
     * @author zhangshuai 2019/12/12
     *
     */
    Specification<T> not(Specification<T> specification);
}
