package com.yuri.gongbu.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.yuri.gongbu.domain.words.Words;
import com.yuri.gongbu.global.GlobalVariable;

@NoArgsConstructor
@Getter
@Setter
public class WordResponseDto {

    private Integer userId;
    private String wordName;
    private String wordPronunciation;
    private String wordMeaning;
    private String wordExample;
    private Integer wordHits;
    private Integer wordLike;
    
    public WordResponseDto(Words word) {
        userId = word.getUserId();
        wordName = word.getWordName();
        wordPronunciation = word.getWordPronunciation();
        wordMeaning = word.getWordMeaning();
        wordExample = word.getWordExample();
        wordHits = word.getWordHits();
        wordLike = word.getWordLike();
    }
}