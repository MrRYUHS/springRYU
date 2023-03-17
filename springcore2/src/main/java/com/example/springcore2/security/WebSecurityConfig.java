package com.example.springcore2.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> {
                            try {
                                authz

                                        .anyRequest().authenticated()
                                        .and()
                                        // 로그인 기능 허용
                                        .formLogin()
                                        .loginPage("/user/login")
                                        .defaultSuccessUrl("/")
                                        .failureUrl("/user/login?error")
                                        .permitAll();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                )
                .httpBasic(withDefaults());
        return http.build();
    }

}