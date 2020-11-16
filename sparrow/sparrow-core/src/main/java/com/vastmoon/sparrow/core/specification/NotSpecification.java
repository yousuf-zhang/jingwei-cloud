package com.vastmoon.sparrow.core.specification;

import lombok.AllArgsConstructor;

/**
 * <p> ClassName: NotSpecification
 * <p> Description: 或关系实现类
 *
 * @author zhangshuai 2019/12/12
 */
@AllArgsConstructor
public class NotSpecification<T> extends AbstractCompositeSpecification<T> {
    private final Specification<T> left;
    private final Specification<T> right;

    @Override
    public boolean isSatisfiedBy(T candidate) {
        return !left.isSatisfiedBy(candidate) && !right.isSatisfiedBy(candidate);
    }
}
