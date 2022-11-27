package com.example.week04.controller;

import com.example.week04.Dto.ProductMypriceRequestDto;
import com.example.week04.Dto.ProductRequestDto;
import com.example.week04.repository.ProductRepository;
import com.example.week04.service.ProductService;
import com.example.week04.utils.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // final로 선언된 멤버 변수를 자동 생성
@RestController // JSON으로 데이터를 주고받음을 선언
public class ProductRestController {

    private final ProductService productService;
    private final ProductRepository productRepository;

    // 등록된 전체 상품 목록 조회
    @GetMapping("/api/products")
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto){
        Product product = new Product(requestDto);
        productRepository.save(product);
        return product;
    }
    @PutMapping("/api/products/{id}")
    public Long updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto){
        return productService.update(id, requestDto);
    }
}
