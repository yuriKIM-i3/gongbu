package com.yuri.gongbu.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import com.yuri.gongbu.service.WordsService;
import com.yuri.gongbu.web.dto.WordHomeResponseDto;
import com.yuri.gongbu.global.GlobalVariable;
import com.yuri.gongbu.util.HomeApiUtil;

@RequiredArgsConstructor
@Controller
public class HomeApiController{

    private final WordsService wordsService;
    List<Integer> postedWords = new ArrayList<Integer>();

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("likeTop10", wordsService.rankTop10ByLike());
        model.addAttribute("hitsTop10", wordsService.rankTop10ByHits());
        model.addAttribute("cardWord", wordsService.getFirstWords());

        return "index";
    }

    @GetMapping("/word/random")
    @ResponseBody
    public String showRandomWord() {
        return HomeApiUtil.getWordRandom(wordsService.getAllWords(), postedWords);
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }

    @GetMapping("/health")
    @ResponseBody
    public String checkHeathy() {
        return "I'm healthy!";
    }
}