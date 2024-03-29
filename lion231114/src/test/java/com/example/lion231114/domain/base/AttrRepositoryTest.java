package com.example.lion231114.domain.base;

import com.example.lion231114.domain.attr.entity.Attr;
import com.example.lion231114.domain.attr.repository.AttrRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class AttrRepositoryTest {
    @Autowired
    private AttrRepository arrtRepository;

    @DisplayName("attr 저장")
    @Test
    void t1() {
        Attr attr = Attr.builder()
                .createDate(LocalDateTime.now())
                .name("age")
                .build();
        arrtRepository.save(attr);
        assertThat(attr.getId()).isGreaterThan(0L);
    }
    @DisplayName("attr 저장, 한번 더")
    @Test
    void t2() {
        Attr attr = Attr.builder()
                .createDate(LocalDateTime.now())
                .name("age")
                .build();
        arrtRepository.save(attr);
        assertThat(attr.getId()).isGreaterThan(0L);
    }

    @DisplayName("select count(*) from attr")
    @Test
    void t3() {
        assertThat(arrtRepository.count()).isEqualTo(0);
    }
}
