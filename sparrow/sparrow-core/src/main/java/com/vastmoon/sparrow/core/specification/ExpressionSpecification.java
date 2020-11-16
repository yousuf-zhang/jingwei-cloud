package com.vastmoon.sparrow.core.specification;

import java.util.Objects;
import java.util.function.Function;

/**
 * <p> ClassName: ExpressionSpecification
 * <p> Description: 把规约模式封装为() -> {} 表达式
 *
 * @author zhangshuai 2019/12/12
 */
public class ExpressionSpecification<T> extends AbstractCompositeSpecification<T> {
    private final Function<T, Boolean> expression;

    public ExpressionSpecification(Function<T, Boolean> expression) {
        if (Objects.isNull(expression)) {
            throw new IllegalArgumentException("expression not null");
        }
        this.expression = expression;
    }

    @Override
    public boolean isSatisfiedBy(T candidate) {
        return this.expression.apply(candidate);
    }
}
