package com.example.week02.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor // 기본생성자를 대신 생성해준다
@Entity // 테이블임을 나타낸다
public class Course extends Timestamped{

    @Id // ID값, Primary Key로 사용하겠다는 뜻
    @GeneratedValue(strategy = GenerationType.AUTO) // 자동 증가 명령
    private Long id;

    @Column(nullable = false) // 컬럼 값이고 반드시 값이 존재해야 함을 나타냄
    private String title;

    @Column(nullable = false)
    private String tutor;

    public String getTitle() {
        return this.title;
    }

    public String getTutor() {
        return this.tutor;
    }

    public Long getId() {
        return this.id;
    }

    public Course(String title, String tutor){
        this.title = title;
        this.tutor = tutor;
    }

    public void update(Course course){
        this.title = course.title;
        this.tutor = course.tutor;
    }
}
