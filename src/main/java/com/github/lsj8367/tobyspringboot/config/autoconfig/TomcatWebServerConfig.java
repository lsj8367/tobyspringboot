package com.github.lsj8367.tobyspringboot.config.autoconfig;

import com.github.lsj8367.tobyspringboot.config.MyAutoConfiguration;
import com.github.lsj8367.tobyspringboot.config.autoconfig.TomcatWebServerConfig.TomcatCondition;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

@MyAutoConfiguration
@Conditional(TomcatCondition.class)
public class TomcatWebServerConfig {

    @Bean("tomcatWebServerFactory")
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    static class TomcatCondition implements Condition {

        @Override
        public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
            return ClassUtils.isPresent("org.apache.catalina.startup.Tomcat",
                context.getClassLoader());
        }
    }


}
