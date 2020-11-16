package com.vastmoon.sparrow.core.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

/**
 *<p> ClassName: AbstractSparrowContext
 * <p> Description: 获取applicationContext的抽象类
 *  用于静态工厂初始化静态类用的
 *
 * @author yousuf 2020/2/22
 */
public abstract class AbstractSparrowContext implements ApplicationContextAware, InitializingBean {
    protected ApplicationContext applicationContext;

    @Override
    @SuppressWarnings("NullableProblems")
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * <p> Title: getBean
     * <p> Description: 获取spring容器中的对象
     *
     * @param targetClz 类
     *
     * @return T
     *
     * @author yousuf 2020/2/22
     *
     */
    @SuppressWarnings({"SameParameterValue", "unchecked"})
    protected <T> T getBean(Class<T> targetClz) {
        T beanInstance = null;
        //byType
        try {
            beanInstance = applicationContext.getBean(targetClz);
        } catch (Exception ignored) {

        }
        //byName
        if (beanInstance == null) {
            String simpleName = targetClz.getSimpleName();
            simpleName = Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
            beanInstance = (T) applicationContext.getBean(simpleName);
        }
        return beanInstance;
    }

    protected Resource getResource(String location) {
        return applicationContext.getResource(location);
    }

}
