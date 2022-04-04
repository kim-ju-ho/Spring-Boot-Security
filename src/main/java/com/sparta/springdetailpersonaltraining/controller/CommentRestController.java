package com.sparta.springdetailpersonaltraining.controller;

import com.sparta.springdetailpersonaltraining.models.Comment;
import com.sparta.springdetailpersonaltraining.models.CommentRequestDto;
import com.sparta.springdetailpersonaltraining.repository.CommentRepository;
import com.sparta.springdetailpersonaltraining.service.CommentService;
import com.sparta.springdetailpersonaltraining.service.ContentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CommentRestController {
    private final CommentRepository commentRepository;
    private  final CommentService commentService;


    // 댓글 등록
    @PostMapping("/api/comment")
    public Comment createComment(@RequestBody CommentRequestDto commentRequestDto){
        Comment comment = new Comment(commentRequestDto);
        return commentRepository.save(comment);
    }
    // 댓글 수정
    @PutMapping("/api/comment/{id}")
    public Long updateComment(@PathVariable Long id,@RequestBody CommentRequestDto commentRequestDto){
        commentService.update(id,commentRequestDto);
        return id;
    }
    //댓글 삭제
    @DeleteMapping("/api/comment/{id}")
    public Long deleteComment(@PathVariable Long id){
        commentRepository.deleteById(id);
        return id;
    }

}
