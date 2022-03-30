package com.sparta.springdetailpersonaltraining.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sparta.springdetailpersonaltraining.models.KakaoUserInfoDto;
import com.sparta.springdetailpersonaltraining.models.SignupRequestDto;
import com.sparta.springdetailpersonaltraining.service.KakaoUserService;
import com.sparta.springdetailpersonaltraining.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    private final UserService userService;
    private final KakaoUserService kakaoUserService;

    @Autowired
    public UserController(UserService userService, KakaoUserService kakaoUserService){
        this.kakaoUserService = kakaoUserService;
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
    public String registerUser(Model model, SignupRequestDto requestDto, @RequestParam String passwordConfirm) throws IllegalAccessException {
        String validChkStr = userService.registerUser(requestDto, passwordConfirm);
        System.out.println(validChkStr);
       if(validChkStr.equals("true")){
           return "redirect:/user/login";
       }
       model.addAttribute("validStr",validChkStr);
        return "signup";


    }

    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(@RequestParam String code) throws JsonProcessingException {
        kakaoUserService.kakaoLogin(code);
        return "redirect:/";
    }

}
