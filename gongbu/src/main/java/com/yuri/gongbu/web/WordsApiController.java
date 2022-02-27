package com.yuri.gongbu.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Random;

import com.yuri.gongbu.service.WordsService;
import com.yuri.gongbu.domain.words.WordsRepository;
import com.yuri.gongbu.domain.words.Words;
import com.yuri.gongbu.global.GlobalVariable;
import com.yuri.gongbu.web.dto.WordsListRequestDto;

@RequiredArgsConstructor
@Controller
public class WordsApiController{

    private final WordsService wordsService;
    private final WordsRepository wordsRepository;

    @GetMapping("/list")
    public String showAll(@RequestParam(defaultValue = "1") Integer page, Model model) {
        PageRequest pageable = PageRequest.of(page - 1, GlobalVariable.WORD_PAGE_SIZE);
        model.addAttribute("result", wordsService.findByDeleteFlgZero(pageable));
        model.addAttribute("isSorted", GlobalVariable.FALSE);

        return "list";
    }

    @GetMapping("/list/sort")
    public String showWordsSortedBy(@RequestParam(defaultValue = "1") Integer page, @RequestParam("sort") String sortedBy, Model model) {
        if (GlobalVariable.sorts.contains(sortedBy)) {
            PageRequest pageable = PageRequest.of(page - 1, GlobalVariable.WORD_PAGE_SIZE, Sort.by(Sort.Direction.DESC, sortedBy));
            model.addAttribute("result", wordsService.findByDeleteFlgZero(pageable));
            model.addAttribute("sortedBy", sortedBy);
            model.addAttribute("isSorted", GlobalVariable.TRUE);

            return "list";
        } else {
            return "error";
        }
        
    }

    @GetMapping("/list/search")
    public String searchWord(@RequestParam(defaultValue = "1") Integer page, WordsListRequestDto requestDto, Model model){
        PageRequest pageable = PageRequest.of(page - 1, GlobalVariable.WORD_PAGE_SIZE);
        model.addAttribute("result", wordsService.findByDeleteFlgAndWordNameLike(requestDto.getKeyword(), pageable));
        model.addAttribute("requestDto", requestDto);

        return "list_search";
    }

    @GetMapping("/dummy")
    public String addDummy() {
        int userId = 1;
        String wordName = "mbti";
        String wordPronunciation = "엠비티아이";
        String wordMeaning = "사기";

        Random rand = new Random();
        for(int i = 0; i < 30; i++) {
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

    @GetMapping("/dummy2")
    public String addDummy2() {
        int userId = 1;
        String wordName = "많관부";
        String wordPronunciation = "많관부";
        String wordMeaning = "많은 관심 부탁드립니다 의 줄임말";

        Random rand = new Random();
        for(int i = 0; i < 26; i++) {
            wordsRepository.save(Words.builder()
                            .userId(userId)
                            .wordName(wordName + i)
                            .wordPronunciation(wordPronunciation)    
                            .wordMeaning(wordMeaning)         
                            .wordExample("gongbu 많관부 입니다 ^^")
                            .wordHits(i)
                            .wordLike(i)
                            .deleteFlg(0)
                            .build());
        }
        return "insert";
    }
}