package com.github.lsj8367.tobyspringboot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class HelloServiceTest {

    @Test
    void simpleHelloService() {
        SimpleHelloService service = new SimpleHelloService();
        final String ret = service.sayHello("Test");

        assertThat(ret).isEqualTo("Hello Test");
    }

}
