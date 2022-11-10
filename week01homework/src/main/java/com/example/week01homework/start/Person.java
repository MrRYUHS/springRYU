package com.example.week01homework.start;

import com.example.week01homework.models.PersonRequestDto;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Person{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String job;
    @Column(nullable = false)
    private int age;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public int getAge() {
        return age;
    }

    public Person(String name, String job, int age){
        this.name = name;
        this.job = job;
        this.age = age;
    }
    public void update(PersonRequestDto requestDto){
        this.name = requestDto.getName();
        this.job = requestDto.getJob();
        this.age = requestDto.getAge();
    }
    public Person(PersonRequestDto requestDto){
        this.name = requestDto.getName();
        this.job = requestDto.getJob();
        this.age = requestDto.getAge();
    }
}
