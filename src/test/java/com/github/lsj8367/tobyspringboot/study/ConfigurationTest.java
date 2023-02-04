package com.github.lsj8367.tobyspringboot.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.web.reactive.HttpHandlerAutoConfiguration.AnnotationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

class ConfigurationTest {

    @Test
    void configuration() {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(MyConfig.class);
        ctx.refresh();

        final Bean1 bean1 = ctx.getBean(Bean1.class);
        final Bean2 bean2 = ctx.getBean(Bean2.class);

        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    @Test
    void proxyCommonMethod() {
        final MyConfigProxy myConfigProxy = new MyConfigProxy();

        final Bean1 bean1 = myConfigProxy.bean1();
        final Bean2 bean2 = myConfigProxy.bean2();

        Assertions.assertThat(bean1.common).isSameAs(bean2.common);
    }

    static class MyConfigProxy extends MyConfig {

        private Common common;

        @Override
        Common common() {
            if (this.common == null) {
                this.common = super.common();
            }

            return this.common;
        }

    }

    @Configuration
    static class MyConfig {
        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        public Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        public Bean2 bean2() {
            return new Bean2(common());
        }

    }


    static class Bean1 {
        private final Common common;

        Bean1(final Common common) {
            this.common = common;
        }

    }

    static class Bean2 {
        private final Common common;

        Bean2(final Common common) {
            this.common = common;
        }

    }

    static class Common {

    }

    // bean1 <- common
    // bean2 <- common

}
