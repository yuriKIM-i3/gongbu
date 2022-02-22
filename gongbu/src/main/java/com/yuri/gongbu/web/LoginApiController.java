package com.yuri.gongbu.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;


@RequiredArgsConstructor
@Controller
public class LoginApiController{

    @GetMapping("/loginPage")
    public String logIn() {
        return "login_page";
    }
}