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

@RequiredArgsConstructor
@Controller
public class HomeApiController{

    private final WordsService wordsService;
    // List<Integer> postedWords = new ArrayList<Integer>();

    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("likeTop10", wordsService.rankTop10ByLike());
        model.addAttribute("hitsTop10", wordsService.rankTop10ByHits());
        model.addAttribute("cardWord", wordsService.getFirstWords());
        // postedWords.put(wordsService.getFirstWords().getWordId());

        return "index";
    }

    @GetMapping("/word/random")
    @ResponseBody
    public String getWordRandom() {
        List<WordHomeResponseDto> wordsList = wordsService.getAllWords();
        List<Integer> wordsId = wordsList.stream().map(id -> id.getWordId()).collect(Collectors.toList());
        Random random = new Random();
        int randomValue = random.nextInt(wordsId.size());

        WordHomeResponseDto randomWord = null;
        for (WordHomeResponseDto dto : wordsList) {
            if(dto.getWordId() == wordsList.get(randomValue).getWordId()){
                // postedWords.put(dto.getWordId());
                randomWord = dto;
            }
        }
        
        return getJson(randomWord);
    }

    private String getJson(WordHomeResponseDto dto){
        String retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            retVal = objectMapper.writeValueAsString(dto);
        } catch (JsonProcessingException e) {
            System.err.println(e);
        }
        return retVal;
    }
}