package com.example.lion231114.domain.attr.service;

import com.example.lion231114.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemService {
    private final MemberRepository memberRepository;

    public boolean isSampleDataCreated() {
        return memberRepository.count() > 0;
    }
}