package com.example.springcore2.service;

import com.example.springcore2.dto.ProductMypriceRequestDto;
import com.example.springcore2.dto.ProductRequestDto;
import com.example.springcore2.model.Product;
import com.example.springcore2.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public Product createProduct(ProductRequestDto requestDto){
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto);

        productRepository.save(product);

        return product;
    }

    public Product updateProduct(Long id, ProductMypriceRequestDto requestDto){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("해당 아이디가 존재하지 않습니다."));

        int myprice = requestDto.getMyprice();
        product.setMyprice(myprice);
        productRepository.save(product);

        return product;
    }

    public List<Product> getProducts(){
        List<Product> products = productRepository.findAll();

        return products;
    }
}
