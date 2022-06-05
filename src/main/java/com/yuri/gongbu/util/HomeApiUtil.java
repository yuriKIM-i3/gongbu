package com.yuri.gongbu.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.Collections;

import com.yuri.gongbu.web.dto.WordHomeResponseDto;
import com.yuri.gongbu.global.GlobalVariable;

public class HomeApiUtil {

    List<Integer> postedWords = Collections.emptyList();

    public static String getWordRandom(List<WordHomeResponseDto> wordsList, List<Integer> postedWords) {
        List<Integer> wordsId = wordsList.stream().map(word -> word.getWordId()).collect(Collectors.toList());
        Random random = new Random();
        int randomWordId = random.nextInt(wordsId.size());

        if (postedWords.size() == GlobalVariable.POSTED_WORDS_MAX_SIZE) {
            postedWords.removeAll(postedWords);
        }

        while (postedWords.contains(randomWordId)){
            randomWordId = random.nextInt(wordsId.size());
        }
        postedWords.add(randomWordId);

        WordHomeResponseDto randomWord = null;
        for (WordHomeResponseDto dto : wordsList) {
            if(dto.getWordId() == wordsList.get(randomWordId).getWordId()){
                randomWord = dto;
            }
        }

        return getJson(randomWord);
    }

    public static String getJson(WordHomeResponseDto dto){
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