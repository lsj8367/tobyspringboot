package com.github.lsj8367.tobyspringboot;

import static org.assertj.core.api.Assertions.assertThat;
import com.github.lsj8367.tobyspringboot.helloboot.HelloDecorator;
import com.github.lsj8367.tobyspringboot.helloboot.SimpleHelloService;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {

    @Test
    void simpleHelloService() {
        SimpleHelloService service = new SimpleHelloService();
        final String ret = service.sayHello("Test");

        assertThat(ret).isEqualTo("Hello Test");
    }

    @Test
    void helloDecorator() {
        final HelloDecorator helloDecorator = new HelloDecorator(name -> name);
        final String actual = helloDecorator.sayHello("Test");
        assertThat(actual).isEqualTo("*Test*");
    }

}
