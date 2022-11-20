package com.example.week03.service;

import com.example.week03.domain.Memo;
import com.example.week03.domain.MemoRepository;
import com.example.week03.medels.MemoRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemoService {

    private final MemoRepository memoRepository;

    public Long update(Long id, MemoRequestDto requestDto){
        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
        memo.update(requestDto);
        return memo.getId();
    }
}
