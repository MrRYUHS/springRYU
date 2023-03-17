package com.example.springcore1.service;

import com.example.springcore1.dto.ProductMypriceRequestDto;
import com.example.springcore1.dto.ProductRequestDto;
import com.example.springcore1.model.Product;
import com.example.springcore1.repository.ProductRepository;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    // 멤버 변수 선언
    private final ProductRepository productRepository;

    // 생성자 : ProductService() 생성될 때 호출됨
    public ProductService(){
        this.productRepository = new ProductRepository();
    }

    public List<Product> getProducts() throws SQLException {
        return productRepository.getProducts();
    }

    public Product createProduct(ProductRequestDto requestDto) throws SQLException {
        // 요청받은 DTO 로 DB에 저장할 객체 만들기
        Product product = new Product(requestDto);
        productRepository.createProduct(product);
        return product;
    }

    public Product updateProduct(Long id, ProductMypriceRequestDto requestDto) throws SQLException {
        Product product = productRepository.getProduct(id);
        if (product == null) {
            throw new NullPointerException("해당 아이디가 존재하지 않습니다.");
        }
        int myPrice = requestDto.getMyprice();
        productRepository.updateProductMyPrice(id, myPrice);
        return product;
    }
}
