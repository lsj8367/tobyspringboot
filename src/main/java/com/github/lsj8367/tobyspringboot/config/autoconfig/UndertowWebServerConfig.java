package com.github.lsj8367.tobyspringboot.config.autoconfig;

import com.github.lsj8367.tobyspringboot.config.ConditionalMyOnClass;
import com.github.lsj8367.tobyspringboot.config.MyAutoConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;

@MyAutoConfiguration
@ConditionalMyOnClass("io.undertow.Undertow")
public class UndertowWebServerConfig {

    @Value("${contextPath}")
    private String contextPath;

    @Bean("undertowWebServerFactory")
    @ConditionalOnMissingBean // 조건에 맞는게 있다면 이 빈 생성을 해주지 않는다.
    public ServletWebServerFactory servletWebServerFactory() {
        final UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();

        factory.setContextPath(this.contextPath);
        return factory;
    }

}
