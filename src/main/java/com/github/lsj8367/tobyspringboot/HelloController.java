package com.github.lsj8367.tobyspringboot;

import java.util.Objects;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(final HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public String hello(final String name) {
        if (Strings.isEmpty(name)) {
            throw new IllegalArgumentException();
        }
        return helloService.sayHello(name);
    }

}
