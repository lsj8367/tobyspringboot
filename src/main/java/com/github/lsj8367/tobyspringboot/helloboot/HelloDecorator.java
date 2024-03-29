package com.github.lsj8367.tobyspringboot.helloboot;

import com.github.lsj8367.tobyspringboot.helloboot.HelloService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class HelloDecorator implements HelloService {

    private final HelloService helloService;

    public HelloDecorator(final HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String sayHello(final String name) {
        return "*" + helloService.sayHello(name) + "*";
    }
}
