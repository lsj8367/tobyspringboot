package com.github.lsj8367.tobyspringboot;

import com.github.lsj8367.tobyspringboot.helloboot.MySpringBootAnnotation;
import org.springframework.boot.SpringApplication;

@MySpringBootAnnotation
public class TobyspringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(TobyspringbootApplication.class, args);
    }

}
