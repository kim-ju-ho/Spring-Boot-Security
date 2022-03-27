package com.sparta.springdetailpersonaltraining.controller;

import com.sparta.springdetailpersonaltraining.models.SignupRequestDto;
import com.sparta.springdetailpersonaltraining.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){

        this.userService = userService;
    }

    // 로그인 페이로 이동
    @GetMapping("/user/login")
    public String login(){
        return "login";
    }

    // 회원가입 페이지로 이동
    @GetMapping("/user/signup")
    public String signup(){
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto) throws IllegalAccessException {
        userService.registerUser(requestDto);
        return "redirect:/user/login";
    }



}
