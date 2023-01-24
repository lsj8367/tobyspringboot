package com.github.lsj8367.tobyspringboot;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class TobyspringbootApplication {

    public static void main(String[] args) {
        final GenericApplicationContext applicationContext = new GenericApplicationContext();
        applicationContext.registerBean(SimpleHelloService.class);
        applicationContext.registerBean(HelloController.class);
        applicationContext.refresh(); // 이 메소드를 사용하면 Java 기반 구성, XML 파일, 속성 파일,
        // 관계형 데이터베이스 스키마 또는 일부 다른 형식일 수 있는 구성의 영구 표현을 로드하거나 새로 고친다.

        final ServletWebServerFactory webServerFactory = new TomcatServletWebServerFactory();
        final WebServer webServer = webServerFactory.getWebServer(servletContext -> {
            servletContext.addServlet("frontController", new HttpServlet() {
                @Override
                protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
                    if ("/hello".equals(req.getRequestURI()) && HttpMethod.GET.name().equals(req.getMethod())) {
                        final String name = req.getParameter("name");

                        final HelloController helloController = applicationContext.getBean(HelloController.class);
                        final String hello = helloController.hello(name);

                        resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
                        resp.getWriter().print(hello);
                    } else {
                        resp.setStatus(HttpStatus.NOT_FOUND.value());
                    }
                }
            }).addMapping("/*");
        });
        webServer.start();
    }

}
