package com.vastmoon.sparrow.core.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * <p> ClassName: ApplicationContextHolder
 * <p> Description: springContext辅助类
 *
 * @author zhangshuai 2019/12/6
 */
@Slf4j
@Service
@Lazy(false)
public class ApplicationContextHolder implements ApplicationContextAware, DisposableBean {
    private static ApplicationContext applicationContext = null;

    @SuppressWarnings("rawtypes")
    private static BeanDefinitionBuilder getBeanDefinitionBuilder(Class clazz) {
        return BeanDefinitionBuilder.genericBeanDefinition(clazz);
    }

    public static Resource getResource(String fileName) {
        return applicationContext.getResource(fileName);
    }

    /**
     * <p> Title: setApplicationContext
     * <p> Description: 初始化成员变量
     *
     * @param applicationContext context
     *
     * @author zhangshuai 2019/11/6
     *
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;
    }

    public static void clearHolder() {
        if (log.isDebugEnabled()) {
            log.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
        }
        applicationContext = null;
    }

    /**
     * <p> Title: getBean
     * <p> Description: 手动获取spring容器中的bean对象
     *
     * @param targetClz 类
     *
     * @return T
     *
     * @author zhangshuai 2019/11/6
     *
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(Class<T> targetClz) {
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

    /**
     * <p> Title: getBean
     * <p> Description: 通过名字获取类
     *
     * @param name 名字
     * @param requiredType 类型
     *
     * @return T
     *
     * @author zhangshuai 2019/11/6
     *
     */
    public static <T> T getBean(String name, Class<T> requiredType) {
        return applicationContext.getBean(name, requiredType);
    }

    /**
     * <p> Title: getBean
     * <p> Description: 根据名字获取Bean
     *
     * @param name 名字
     *
     * @return java.lang.Object
     *
     * @author zhangshuai 2019/11/8
     *
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }
    /**
     * <p> Title: getBeanMapByAnnotation
     * <p> Description: 获取注解的类
     *
     * @param annotationClz 注解
     *
     * @return java.util.Map<java.lang.String,java.lang.Object>
     *
     * @author zhangshuai 2019/11/6
     *
     */
    public static Map<String, Object> getBeanMapByAnnotation(Class<? extends Annotation> annotationClz) {
        return applicationContext.getBeansWithAnnotation(annotationClz);
    }

    @Override
    public void destroy() throws Exception {
        ApplicationContextHolder.clearHolder();
    }
}
