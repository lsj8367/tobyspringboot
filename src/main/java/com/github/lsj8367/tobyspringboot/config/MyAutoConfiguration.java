package com.github.lsj8367.tobyspringboot.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Configuration;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Configuration(proxyBeanMethods = false) // 등록된 빈을 가져와서 추가적으로 의존 주입을 하는경우가 아니라면 false를 주어 프록시를 만들지 않게 하는 옵션
public @interface MyAutoConfiguration {

}
