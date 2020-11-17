package com.vastmoon.sparrow.security.config;

import com.vastmoon.sparrow.core.constant.SecurityConstants;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * <p> ClassName: SparrowSecurityBeanDefinitionRegistrar
 * <p> Description: 注册 { #com.vastmoon.sparrow.security.config.SparrowResourceServerConfigurerAdapter }
 *
 * @author yousuf 2020/11/12
 */
@Slf4j
public class SparrowSecurityBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    /**
     * Description: 根据注解值动态注入资源服务器的相关属性
     *
     * @param importingClassMetadata 注解信息
     * @param registry 注册器
     *
     * @author yousuf 2020/11/12
     *
     */
    @Override
    public void registerBeanDefinitions(@NonNull AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        if (registry.isBeanNameInUse(SecurityConstants.RESOURCE_SERVER_CONFIGURER)) {
            log.warn("本地存在资源服务器配置，覆盖默认配置:" + SecurityConstants.RESOURCE_SERVER_CONFIGURER);
            return;
        }

        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(SparrowResourceServerConfigurerAdapter.class);
        registry.registerBeanDefinition(SecurityConstants.RESOURCE_SERVER_CONFIGURER, beanDefinition);

    }
}
