package com.example.week04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling // 스프링 부트에서 스케줄러가 작동하게 한다
@EnableJpaAuditing // 시간 자동 변경 가능
@SpringBootApplication // 스프링 부트임을 선언
public class Week04Application {
    public static void main(String[] args) {
        SpringApplication.run(Week04Application.class, args);
    }

}
