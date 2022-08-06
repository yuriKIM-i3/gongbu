package com.yuri.gongbu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginApiController {

    @GetMapping("/loginPage")
    public String logIn() {
        return "login_page";
    }
}
