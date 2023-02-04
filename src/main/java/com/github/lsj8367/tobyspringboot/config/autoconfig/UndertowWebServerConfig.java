package com.github.lsj8367.tobyspringboot.config.autoconfig;

import com.github.lsj8367.tobyspringboot.config.MyAutoConfiguration;
import com.github.lsj8367.tobyspringboot.config.autoconfig.UndertowWebServerConfig.UndertowCondition;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

@MyAutoConfiguration
@Conditional(UndertowCondition.class)
public class UndertowWebServerConfig {

    @Bean("jettyWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory() {
        return new UndertowServletWebServerFactory();
    }

    static class UndertowCondition implements Condition {

        @Override
        public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
            return ClassUtils.isPresent("io.undertow.Undertow", context.getClassLoader());
        }
    }
}
