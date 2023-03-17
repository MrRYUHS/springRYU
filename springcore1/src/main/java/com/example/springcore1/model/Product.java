package com.example.springcore1.model;

import com.example.springcore1.dto.ProductRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter // get 함수 일괄 생성
@NoArgsConstructor // 기본 생성자 생성
@Entity // DB 테이블
public class Product extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO) // ID가 자동 생성 및 증가
    @Id
    private Long id;

    @Column(nullable = false) // 반드시 값을 가지도록 함
    private String title;

    @Column(nullable = false)
    private String image;

    @Column(nullable = false)
    private String link;

    @Column(nullable = false)
    private int lprice;

    @Column(nullable = false)
    private int myprice;

    // 관심 상품 생성 시 이용
    public Product(ProductRequestDto requestDto){
        this.title = requestDto.getTitle();
        this.image = requestDto.getImage();
        this.link = requestDto.getLink();
        this.lprice = requestDto.getLprice();
        this.myprice = 0;
    }
}
