package com.sparta.springdetailpersonaltraining.controller;

import com.sparta.springdetailpersonaltraining.security.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(userDetails.getUser() !=null){
            model.addAttribute("username", userDetails.getUsername());
        }

        return "index.html";
    }
}
