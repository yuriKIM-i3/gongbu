package com.yuri.gongbu.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;

import com.yuri.gongbu.service.WordsService;

@RequiredArgsConstructor
@Controller
public class HomeApiController{

    private final WordsService wordsService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("likeTop10", wordsService.rankTop10ByLike());

        return "index";
    }

}