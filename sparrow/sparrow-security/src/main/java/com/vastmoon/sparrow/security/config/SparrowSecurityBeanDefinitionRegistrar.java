package com.vastmoon.sparrow.security.config;

import com.vastmoon.sparrow.core.constant.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * <p> ClassName: SparrowSecurityBeanDefinitionRegistrar
 * <p> Description: 配置资源服务器
 *
 * @author yousuf 2020/11/30
 */
@Slf4j
public class SparrowSecurityBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * 根据注解值动态注入资源服务器的相关属性
     * @param metadata 注解信息
     * @param registry 注册器
     */
    @SuppressWarnings("NullableProblems")
    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        if (registry.isBeanNameInUse(SparrowResourceServerConfigurerAdapter.RESOURCE_SERVER_CONFIGURER)) {
            log.warn("本地存在资源服务器配置，覆盖默认配置:" + SparrowResourceServerConfigurerAdapter.RESOURCE_SERVER_CONFIGURER);
            return;
        }

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(SparrowResourceServerConfigurerAdapter.class);
        registry.registerBeanDefinition(SparrowResourceServerConfigurerAdapter.RESOURCE_SERVER_CONFIGURER, beanDefinition);

    }
}
