package com.yuri.gongbu.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;

import java.util.Random;

import com.yuri.gongbu.service.WordsService;
import com.yuri.gongbu.domain.words.WordsRepository;
import com.yuri.gongbu.domain.words.Words;

@RequiredArgsConstructor
@Controller
public class WordsApiController{

    private final WordsService wordsService;
    private final WordsRepository wordsRepository;

    @GetMapping("/list")
    public String hello(Model model) {
        model.addAttribute("words", wordsService.findAll());
        return "list";
    }

    @GetMapping("/dummy")
    public String addDummy() {
        int userId = 1;
        String wordName = "mbti";
        String wordPronunciation = "엠비티아이";
        String wordMeaning = "사기";

        Random rand = new Random();
        for(int i = 0; i < rand.nextInt(10); i++) {
            wordsRepository.save(Words.builder()
                            .userId(userId)
                            .wordName(wordName + i)
                            .wordPronunciation(wordPronunciation)    
                            .wordMeaning(wordMeaning)         
                            .wordExample("엠비티아이 검사 했어?")
                            .wordHits(i)
                            .wordLike(i)
                            .deleteFlg(0)
                            .build());
        }

        return "insert";
    }
}