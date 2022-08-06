package com.yuri.gongbu.web.dto;

import com.yuri.gongbu.domain.words.Words;
import com.yuri.gongbu.global.GlobalVariable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class WordResponseDto {

    private Integer wordId;
    private Integer userId;
    private String wordName;
    private String wordPronunciation;
    private String wordMeaning;
    private String wordExample;
    private Integer wordHits;
    private Integer wordLike;

    public WordResponseDto(Words word) {
        wordId = word.getWordId();
        userId = word.getUserId();
        wordName = word.getWordName();
        wordPronunciation = word.getWordPronunciation();
        wordMeaning = word.getWordMeaning();
        wordExample = word.getWordExample();
        wordHits = word.getWordHits();
        wordLike = word.getWordLike();
    }
}
