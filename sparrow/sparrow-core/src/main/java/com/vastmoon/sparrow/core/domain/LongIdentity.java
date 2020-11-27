package com.vastmoon.sparrow.core.domain;

import com.vastmoon.sparrow.core.exception.IllegalParamException;

import java.util.Objects;

/**
 * <p> ClassName: LongIdentity
 * <p> Description: Long 类型主键
 *
 * @author zhangshuai 2019/12/25
 */
public class LongIdentity implements Identity<Long> {
    private Long value;
    @Override
    public Long value() {
        return this.value;
    }

    private LongIdentity(Long value) {
        if (Objects.isNull(value)) {
            throw new IllegalParamException("主键错误")
                    .addError("LongIdentity", "主键错误");
        }
        this.value = value;
    }

    public static LongIdentity of(Long value) {
        return new LongIdentity(value);
    }
}
