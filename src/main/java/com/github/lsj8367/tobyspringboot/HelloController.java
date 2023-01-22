package com.github.lsj8367.tobyspringboot;

import java.util.Objects;

public class HelloController {

    public String hello(final String name) {
        SimpleHelloService service = new SimpleHelloService();
        return service.sayHello(Objects.requireNonNull(name));
    }

}
