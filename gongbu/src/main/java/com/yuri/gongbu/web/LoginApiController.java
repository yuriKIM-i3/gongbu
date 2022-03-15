package com.yuri.gongbu.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class LoginApiController{

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/loginPage")
    public String logIn() {
        return "login_page";
    }
}