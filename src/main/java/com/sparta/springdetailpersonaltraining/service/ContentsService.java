package com.sparta.springdetailpersonaltraining.service;


import com.sparta.springdetailpersonaltraining.models.Contents;
import com.sparta.springdetailpersonaltraining.models.ContentsRequestDto;

import com.sparta.springdetailpersonaltraining.repository.ContentsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class ContentsService {

    private final ContentsRepository ContentsRepository;

    @Transactional
    public Long update(Long id, ContentsRequestDto requestDto) {
        Contents Contents = ContentsRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        Contents.update(requestDto);
        return Contents.getId();
    }
}