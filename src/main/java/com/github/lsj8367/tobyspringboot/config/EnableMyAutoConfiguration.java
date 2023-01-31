package com.github.lsj8367.tobyspringboot.config;

import com.github.lsj8367.tobyspringboot.config.autoconfig.DispatcherServletConfig;
import com.github.lsj8367.tobyspringboot.config.autoconfig.TomcatWebServerConfig;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({DispatcherServletConfig.class, TomcatWebServerConfig.class})
public @interface EnableMyAutoConfiguration {

}
