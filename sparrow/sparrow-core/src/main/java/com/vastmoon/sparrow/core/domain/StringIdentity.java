package com.vastmoon.sparrow.core.domain;

import com.vastmoon.sparrow.core.exception.IllegalParamException;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * <p> ClassName: StringIdentity
 * <p> Description: 字符串主键
 *
 * @author zhangshuai 2019/12/25
 */
@ToString
@EqualsAndHashCode
public class StringIdentity implements Identity<String> {
    private static final long serialVersionUID = -7391371991171257378L;
    private String value;
    @Override
    public String value() {
        return this.value;
    }

    private StringIdentity(String value) {
        if (Objects.isNull(value)) {
            throw new IllegalParamException("主键错误")
                    .addError("StringIdentity", "主键错误");
        }
        this.value = value;
    }

    public static StringIdentity of(String value) {
        return new StringIdentity(value);
    }

   public static Optional<StringIdentity> optional(String value) {
        return StringUtils.isBlank(value) ? Optional.empty() : Optional.of(new StringIdentity(value));
   }
}
