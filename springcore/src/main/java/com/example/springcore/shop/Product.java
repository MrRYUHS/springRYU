package com.example.springcore.shop;

import com.example.springcore.dto.ProductRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter // get 함수 일괄 생성
@NoArgsConstructor // 기본생성자 생성
@Entity // DB 테이블 역할
public class Product extends Timestamped{

    // ID가 자동 생성 및 증가
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long id;

    // 반드시 값을 가지도록 함
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String image;
    @Column(nullable = false)
    private String link;
    @Column(nullable = false)
    private int lprice;
    @Column(nullable = false)
    private int myprice;
    @Column(nullable = false)
    private Long userId;

    // 관심 상품 생성 시 이용
    public Product(ProductRequestDto requestDto){
        // 관심상품을 등록한 회원 Id 저장
        this.title = requestDto.getTitle();
        this.image = requestDto.getImage();
        this.link = requestDto.getLink();
        this.lprice = requestDto.getLprice();
        this.myprice = 0;
        this.userId = userId;
    }

    public void updateMyPrice(int myPrice) {
    }
}
