package com.example.springcore.repository;

import com.example.springcore.shop.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
