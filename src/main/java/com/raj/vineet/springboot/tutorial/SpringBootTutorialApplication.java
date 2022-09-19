package com.raj.vineet.springboot.tutorial;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class SpringBootTutorialApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootTutorialApplication.class, args);
    }
}
