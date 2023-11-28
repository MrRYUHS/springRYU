package com.example.lion231114.domain.member.repository;

import com.example.lion231114.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUsername(String username);
    Optional<Member> findFirstByOrderByIdDesc();
}
