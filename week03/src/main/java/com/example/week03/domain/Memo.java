package com.example.week03.domain;

import com.example.week03.medels.MemoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor //기본생성자
@Getter
@Entity // 테이블과 연계됨을 스프링에 알려준다
public class Memo extends Timestamped{ //생성, 수정 시간을 자동으로 만듬

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String contents;

    public Memo(String username, String contents){
        this.username = username;
        this.contents = contents;
    }
    public Memo(MemoRequestDto requesDto){
        this.username = requesDto.getUsername();
        this.contents = requesDto.getContents();
    }
    public void update(MemoRequestDto requestDto){
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
    }
}
