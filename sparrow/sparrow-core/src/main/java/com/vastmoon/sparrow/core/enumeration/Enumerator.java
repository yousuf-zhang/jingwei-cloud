package com.vastmoon.sparrow.core.enumeration;


import com.vastmoon.sparrow.core.exception.framework.FrameworkException;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * <p> ClassName: Enumerator
 * <p> Description: 枚举基础类型
 *
 * @author zhangshuai 2019/12/10
 */
@SuppressWarnings("rawtypes")
public interface Enumerator<V> {
    /**
     * <p> Title: getCode
     * <p> Description: 获取code值
     *
     * @return V
     *
     * @author yousuf zhang 2019/11/4
     **/
    V getCode();

    /**
     * <p> Title: getText
     * <p> Description: 获取文本信息
     *
     * @return java.lang.String
     *
     * @author yousuf zhang 2019/11/4
     **/
    String getText();

    /**
     * <p> Title: find
     * <p> Description: 从指定的枚举类中查找想要的枚举,并返回一个{@link Optional},
     * 如果未找到,则返回一个{@link Optional#empty()}
     *
     * @param type 实现了{@link Enumerator}的枚举类
     * @param predicate 判读逻辑
     * @return java.util.Optional<T> 返回枚举类型
     *
     * @author yousuf zhang 2019/11/4
     **/
    static <T extends Enum & Enumerator> Optional<T> find(Class<T> type, Predicate<T> predicate) {
        if (type.isEnum()) {
            return Arrays.stream(type.getEnumConstants())
                    .filter(predicate).findFirst();
        }
        return Optional.empty();
    }

    /**
     * <p> Title: findOptionalByCode
     * <p> Description: 根据枚举的{@link Enumerator#getCode()}来查找.
     *
     * @see this#find(Class, Predicate)
     * @param type 实现了{@link Enumerator}的枚举类
     * @param code 枚举code
     * @return java.util.Optional<T>
     *
     * @author yousuf zhang 2019/11/4
     **/
    static <T extends Enum & Enumerator<?>> Optional<T> findOptionalByCode(Class<T> type, Object code) {
        return find(type, e -> e.getCode() == code || e.getCode().equals(code)
                || String.valueOf(e.getCode()).equalsIgnoreCase(String.valueOf(code)));
    }

    /**
     * <p> Title: findOptionalByText
     * <p> Description: 根据枚举的{@link Enumerator#getText()}来查找.
     *
     * @param type 实现了{@link Enumerator}的枚举类
     * @param text 枚举 text
     * @return java.util.Optional<T>
     *
     * @author yousuf zhang 2019/11/5
     **/
    static <T extends Enum & Enumerator> Optional<T> findOptionalByText(Class<T> type, String text) {
        return find(type, e -> e.getText().equalsIgnoreCase(text));
    }

    /**
     * <p> Title: findByCode
     * <p> Description:  根据枚举的{@link Enumerator#getCode()}来查找.
     *
     * @param type type 实现了{@link Enumerator}的枚举类
     * @param code 枚举code
     * @return T
     * @throws FrameworkException 枚举转换异常
     *
     * @author yousuf zhang 2019/11/17
     **/
    static <T extends Enum & Enumerator<?>> T findByCode(Class<T> type, Object code) {
        return findOptionalByCode(type, code)
                .orElseThrow(() -> new FrameworkException("枚举转换异常"));
    }

    /**
     * <p> Title: findByCode
     * <p> Description:  根据枚举的{@link Enumerator#getText()}来查找.
     *
     * @param type type 实现了{@link Enumerator}的枚举类
     * @param text 枚举text
     * @return T
     * @throws FrameworkException 枚举转换异常
     *
     * @author yousuf zhang 2019/11/17
     **/
    static <T extends Enum & Enumerator<?>> T findByText(Class<T> type, String text) {
        return findOptionalByText(type, text)
                .orElseThrow(() -> new FrameworkException("枚举转换异常"));
    }
}
