package com.example.lion231114.domain.member.service;

import com.example.lion231114.domain.member.entity.Member;
import com.example.lion231114.domain.member.repository.MemberRepository;
import com.example.lion231114.global.rsData.RsData;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public RsData<Member> join(String username, String password) {
        if (findByUsername(username).isPresent()) {
            return new RsData<>("F-1", "이미 사용중인 아이디입니다.");
        }

        password = passwordEncoder.encode(password);
        Member member = Member
                .builder()
                .username(username)
                .password(password)
                .build();

        memberRepository.save(member);

        return new RsData<>(
                "S-1",
                "%s님 환영합니다.".formatted(member.getUsername()),
                member
        );
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Optional<Member> findById(long id) {
        return memberRepository.findById(id);
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    @Transactional
    public void delete(long id) {
        memberRepository.deleteById(id);
    }

    @Transactional
    public void modify(long id, String username, String password) {
        Member member = findById(id).get();
        member.setUsername(username);
        member.setPassword(password);
    }

    public Optional<Member> findLatest() {
        return memberRepository.findFirstByOrderByIdDesc();
    }
}