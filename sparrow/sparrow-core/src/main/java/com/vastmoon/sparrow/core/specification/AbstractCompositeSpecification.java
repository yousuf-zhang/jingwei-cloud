package com.vastmoon.sparrow.core.specification;

/**
 * <p> ClassName: AbstractCompositeSpecification
 * <p> Description: Specification 抽象实现类，继承者只需要实现业务逻辑判断就可以
 *
 * @author zhangshuai 2019/12/12
 */
public abstract class AbstractCompositeSpecification<T> implements Specification<T> {
    @Override
    public Specification<T> and(Specification<T> specification) {
        return new AndSpecification<>(this, specification);
    }

    @Override
    public Specification<T> or(Specification<T> specification) {
        return new OrSpecification<>(this, specification);
    }

    @Override
    public Specification<T> not(Specification<T> specification) {
        return new NotSpecification<>(this, specification);
    }
}
