package com.example.week01homework.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PersonRequestDto {
    private String name;
    private String job;
    private int age;

    public PersonRequestDto(String name, String job, int age){
        this.name = name;
        this.job = job;
        this.age = age;
    }
}
