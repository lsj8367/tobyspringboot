package com.github.lsj8367.tobyspringboot.study;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.AnnotatedTypeMetadata;

class ConditionalTest {

    @Test
    void conditional() {

        ApplicationContextRunner runner = new ApplicationContextRunner();
        runner.withUserConfiguration(Config1.class)
            .run(context -> {
                assertThat(context).hasSingleBean(MyBean.class);
                assertThat(context).hasSingleBean(Config1.class);
            });

        new ApplicationContextRunner().withUserConfiguration(Config2.class)
            .run(context -> {
                assertThat(context).doesNotHaveBean(MyBean.class);
                assertThat(context).doesNotHaveBean(Config2.class);
            });
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional {
        boolean value();
    }


    @Configuration
    @BooleanConditional(true)
    static class Config1 {

        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    @Configuration
    @BooleanConditional(false)
    static class Config2 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }

    }

    static class MyBean {

    }

    private static class BooleanCondition implements Condition {

        @Override
        public boolean matches(final ConditionContext context, final AnnotatedTypeMetadata metadata) {
            final Map<String, Object> attributes = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
            return (boolean) attributes.get("value");
        }
    }
}
