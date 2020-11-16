package com.vastmoon.sparrow.core.common;

import com.google.common.collect.Maps;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * <p> ClassName: AbstractAnnotationBeanMap
 * <p> Description: 用于根据注解获取bean对象
 * 这个类主要通过注解的方式来替代switch循环
 *
 * @author yousuf 2020/2/24
 */
public abstract class AbstractAnnotationBeanMap<A extends Annotation,B> extends AbstractSparrowContext {
    /**
     * <p> Title: getAnnotation
     * <p> Description: 需要缓存的注解类型
     *
     * @return java.lang.Class<A>
     *
     * @author yousuf 2020/2/24
     *
     */
    protected abstract Class<A> getAnnotation();
    /**
     * <p> Title: annotationBeanMap
     * <p> Description: 传递获取到的Bean给实现类
     *
     * @param annotationBeanMap 包含 A 注解的 Bean
     *
     * @author yousuf 2020/2/24
     *
     */
    protected abstract void annotationBeanMap(Map<A, B> annotationBeanMap);

    @Override
    @SuppressWarnings("unchecked")
    public void afterPropertiesSet() throws Exception {
        Map<A, B> annotationBeanMap = Maps.newHashMap();
        Map<String, Object> beanMap = applicationContext.getBeansWithAnnotation(getAnnotation());
        beanMap.values().forEach(bean -> {
            A annotation = (A) AnnotationUtils.findAnnotation(bean.getClass(), getAnnotation());
            annotationBeanMap.put(annotation, (B) bean);
        });
        annotationBeanMap(annotationBeanMap);
    }
}
