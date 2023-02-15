package com.example.springcore3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class Springcore3Application {

    public static void main(String[] args) {
        SpringApplication.run(Springcore3Application.class, args);
    }

}
