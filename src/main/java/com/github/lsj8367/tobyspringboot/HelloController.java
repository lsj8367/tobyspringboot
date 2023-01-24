package com.github.lsj8367.tobyspringboot;

import java.util.Objects;

public class HelloController {

    private final HelloService helloService;

    public HelloController(final HelloService helloService) {
        this.helloService = helloService;
    }

    public String hello(final String name) {
        return helloService.sayHello(Objects.requireNonNull(name));
    }

}
