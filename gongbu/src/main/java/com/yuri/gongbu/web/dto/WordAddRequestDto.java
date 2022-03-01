package com.yuri.gongbu.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import com.yuri.gongbu.domain.words.Words;
import com.yuri.gongbu.global.GlobalVariable;

@NoArgsConstructor
@Getter
@Setter
public class WordAddRequestDto {
    private Integer userId;
    private String wordName;
    private String wordPronunciation;
    private String wordMeaning;
    private String wordExample;
    
    // @Builder
    // public WordAddRequestDto(Integer userId, String wordName, String wordPronunciation, String wordMeaning, String wordExample) {
    //     this.userId = userId;
    //     this.wordName = wordName;
    //     this.wordPronunciation = wordPronunciation;
    //     this.wordMeaning = wordMeaning;
    //     this.wordExample = wordExample;
    // }

    
    public Words toEntity() {
        return Words.builder()
                    .userId(userId)
                    .wordName(wordName)
                    .wordPronunciation(wordPronunciation)
                    .wordMeaning(wordMeaning)
                    .wordExample(wordExample)
                    .wordHits(0)
                    .wordLike(0)
                    .deleteFlg(GlobalVariable.FALSE)
                    .build();
    }
}