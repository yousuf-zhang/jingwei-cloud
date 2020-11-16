package com.vastmoon.sparrow.core.specification;

import lombok.AllArgsConstructor;

/**
 * <p> ClassName: AndSpecification
 * <p> Description: 且关系实现类
 *
 * @author zhangshuai 2019/12/12
 */
@AllArgsConstructor
public class AndSpecification<T> extends AbstractCompositeSpecification<T> {
    private final Specification<T> left;
    private final Specification<T> right;

    @Override
    public boolean isSatisfiedBy(T candidate) {
        return left.isSatisfiedBy(candidate) && right.isSatisfiedBy(candidate);
    }
}
