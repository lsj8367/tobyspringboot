package com.github.lsj8367.tobyspringboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class TobyspringbootApplication {

    public static void main(String[] args) {
        final GenericWebApplicationContext applicationContext = new GenericWebApplicationContext();
        applicationContext.registerBean(HelloController.class);
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.refresh(); // 이 메소드를 사용하면 Java 기반 구성, XML 파일, 속성 파일,
        // 관계형 데이터베이스 스키마 또는 일부 다른 형식일 수 있는 구성의 영구 표현을 로드하거나 새로 고친다.

        final ServletWebServerFactory webServerFactory = new TomcatServletWebServerFactory();
        final WebServer webServer = webServerFactory.getWebServer(servletContext -> {
            servletContext.addServlet("dispatcherServlet",
                    new DispatcherServlet(applicationContext)
                ).addMapping("/*");
        });
        webServer.start();
    }

}
