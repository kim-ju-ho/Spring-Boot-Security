package com.sparta.springdetailpersonaltraining.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(){
//        if(userDetails.getUser() !=null){
//            model.addAttribute("username", userDetails.getUsername());
//        }

        return "index";
    }
}
