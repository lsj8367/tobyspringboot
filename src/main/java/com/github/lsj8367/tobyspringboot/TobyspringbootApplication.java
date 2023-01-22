package com.github.lsj8367.tobyspringboot;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class TobyspringbootApplication {

    public static void main(String[] args) {
        final ServletWebServerFactory webServerFactory = new TomcatServletWebServerFactory();
        final WebServer webServer = webServerFactory.getWebServer(servletContext -> {
            final HelloController helloController = new HelloController();
            servletContext.addServlet("frontController", new HttpServlet() {
                @Override
                protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
                    if ("/hello".equals(req.getRequestURI()) && HttpMethod.GET.name().equals(req.getMethod())) {
                        final String name = req.getParameter("name");

                        final String hello = helloController.hello(name);

                        resp.setStatus(200);
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
