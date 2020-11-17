package com.vastmoon.sparrow.security.annotation;

import com.vastmoon.sparrow.security.config.SparrowResourceServerAutoConfiguration;
import com.vastmoon.sparrow.security.config.SparrowSecurityBeanDefinitionRegistrar;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 * <p> ClassName: EnableSparrowResourceServer
 * <p> Description: 资源服务器启动注解
 *
 * @author yousuf 2020/11/11
 */
@Documented
@Inherited
@EnableResourceServer
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ SparrowResourceServerAutoConfiguration.class, SparrowSecurityBeanDefinitionRegistrar.class })
public @interface EnableSparrowResourceServer {
}
