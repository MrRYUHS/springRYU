package com.example.lion231114.domain.article.repository;

import com.example.lion231114.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findFirstByOrderByIdDesc();
}