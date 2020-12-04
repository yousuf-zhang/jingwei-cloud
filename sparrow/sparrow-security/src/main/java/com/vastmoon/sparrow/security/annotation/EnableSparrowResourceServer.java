package com.vastmoon.sparrow.security.annotation;

import com.vastmoon.sparrow.security.config.SparrowSecurityBeanDefinitionRegistrar;
import com.vastmoon.sparrow.security.config.SparrowSecurityAutoConfig;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.lang.annotation.*;

/**
 * <p> ClassName: EnableSparrowResourceServer
 * <p> Description: 启动全局注解资源配置
 *
 * @author yousuf 2020/11/27
 */
@Documented
@Inherited
@EnableWebSecurity
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Import({ SparrowSecurityAutoConfig.class, SparrowSecurityBeanDefinitionRegistrar.class})

@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public @interface EnableSparrowResourceServer {
}
