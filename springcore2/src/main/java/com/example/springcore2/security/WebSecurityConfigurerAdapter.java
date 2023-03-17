package com.example.springcore2.security;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;

public abstract class WebSecurityConfigurerAdapter {
    public abstract void configure(WebSecurity web);

    protected abstract void configure(HttpSecurity http) throws Exception;
}
