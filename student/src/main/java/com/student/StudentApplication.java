package com.student;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class StudentApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(StudentApplication.class)
                .web(WebApplicationType.SERVLET)
                .run(args);
    }
}
