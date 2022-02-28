package com.yuri.gongbu.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import com.yuri.gongbu.config.auth.dto.SessionUser;
import com.yuri.gongbu.config.auth.LoginUser;

@RequiredArgsConstructor
@Controller
public class IndexApiController{

    @GetMapping("/")
    public String home(Model model, @LoginUser SessionUser user) {
        model.addAttribute("loginedUser", user);
        
        return "index";
    }
}