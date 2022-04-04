package com.sparta.springdetailpersonaltraining.controller;



import com.sparta.springdetailpersonaltraining.models.Comment;
import com.sparta.springdetailpersonaltraining.models.Contents;
import com.sparta.springdetailpersonaltraining.models.ContentsRequestDto;
import com.sparta.springdetailpersonaltraining.repository.CommentRepository;
import com.sparta.springdetailpersonaltraining.repository.ContentsRepository;
import com.sparta.springdetailpersonaltraining.security.UserDetailsImpl;
import com.sparta.springdetailpersonaltraining.service.ContentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ContentsRestController {

    private final ContentsRepository ContentsRepository;
    private final ContentsService ContentsService;
    private final CommentRepository commentRepository;

    // 게시글 전체 조회
    @GetMapping("/api/contents")
    public List<Contents> getContents() {

        return ContentsRepository.findAllByOrderByCreatedAtDesc();
    }

    // 게시글 작성 페이지로 이동
    @GetMapping("/api/write")
    public ModelAndView writeContents(ModelAndView mv, @AuthenticationPrincipal UserDetailsImpl userDetails){
        mv.setViewName("write");
        if(userDetails == null){

            return mv;
        }
        mv.addObject("username", userDetails.getUsername());
        return mv;
    }

    // 게시글 특정 조회 및 코멘트 조회
    @GetMapping("/api/contents/{id}")
    public ModelAndView getContents(ModelAndView mv, @PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        Contents contents =  ContentsRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("contentsId가 존재하지 않습니다."));
        List<Comment> commentList  = commentRepository.findAllByParentsIdOrderByCreatedAtDesc(id);
        mv.addObject("contents",contents);
        mv.addObject("commentList", commentList);
        // null 체크 해야함
        if (userDetails != null){
            mv.addObject("username",userDetails.getUsername());
        }
        mv.setViewName("detail");
        return mv;
    }

    // 게시글 생성
    @PostMapping("/api/contents")
    public Contents createContents(@RequestBody ContentsRequestDto requestDto) {
        Contents Contents = new Contents(requestDto);
        return ContentsRepository.save(Contents);
    }

    // 게시글 수정
    @PutMapping("/api/contents/{id}")
    public Long updateContents(@PathVariable Long id, @RequestBody ContentsRequestDto requestDto) {

        ContentsService.update(id, requestDto);
        return id;
    }

    @DeleteMapping("/api/contents/{id}")
    public Long deleteContents(@PathVariable Long id) {
        ContentsRepository.deleteById(id);
        return id;
    }

}