package com.yuri.gongbu.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class MyPageApiController{

    @GetMapping("/myPage")
    public String goMyPage() {
        return "/my_page/my_page";
    }
}