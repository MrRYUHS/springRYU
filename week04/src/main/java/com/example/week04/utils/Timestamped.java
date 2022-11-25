package com.example.week04.utils;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter // get 함수를 자동 생성
@MappedSuperclass // 멤버 변수가 컬럼이 되도록 함
@EntityListeners(AuditingEntityListener.class) // 변경되었을 때 자동 기록
public class Timestamped {
    @CreatedDate // 최초 생성 지점
    private LocalDateTime createdAt;

    @LastModifiedDate // 마지막 변경 시점점
   private LocalDateTime modifiedAt;
}
