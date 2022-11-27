package com.example.week04.service;

import com.example.week04.Dto.ItemDto;
import com.example.week04.Dto.ProductMypriceRequestDto;
import com.example.week04.repository.ProductRepository;
import com.example.week04.utils.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동 생성
@Service // 서비스임을 선언
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언
    public Long update(Long id, ProductMypriceRequestDto requestDto){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다.")
        );
        product.update(requestDto);
        return id;
    }
    @Transactional // 메소드 동작이 SQL 쿼리문임을 선언
    public Long updateBySearch(Long id, ItemDto itemDto){
        Product product = productRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 아이디가 존재하지 않습니다")
        );
        product.updateByItemDto(itemDto);
        return id;
    }
}
