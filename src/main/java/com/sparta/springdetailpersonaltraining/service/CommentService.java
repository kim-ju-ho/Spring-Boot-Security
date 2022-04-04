package com.sparta.springdetailpersonaltraining.service;

import com.sparta.springdetailpersonaltraining.models.Comment;
import com.sparta.springdetailpersonaltraining.models.CommentRequestDto;
import com.sparta.springdetailpersonaltraining.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    @Autowired
    public CommentService(CommentRepository commentRepository){
        this.commentRepository = commentRepository;
    }

    @Transactional
    public Long update(Long id, CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("코멘트 아이디가 존재하지 않습니다")
        );
        comment.update(commentRequestDto);
        return comment.getId();
    }
}
