package com.example.springcore1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // 시간 자동 변경 가능하게 함
@ServletComponentScan
public class Springcore1Application {

    public static void main(String[] args) {
        SpringApplication.run(Springcore1Application.class, args);
    }

}
