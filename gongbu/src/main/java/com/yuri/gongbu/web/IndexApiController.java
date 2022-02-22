package com.yuri.gongbu.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;


@RequiredArgsConstructor
@Controller
public class IndexApiController{

    @GetMapping("/")
    public String home() {
        return "index";
    }
}