package com.yuri.gongbu.web.dto;

import lombok.Getter;

import com.yuri.gongbu.domain.words.Words;

@Getter
public class WordsListResponseDto {
    private Integer wordId;
    private String wordName;
    private String wordPronunciation;
    private String wordMeaning;
    private Integer wordHits;
    private Integer wordLike;

    public WordsListResponseDto(Words entity) {
        this.wordId = entity.getWordId();
        this.wordName = entity.getWordName();
        this.wordPronunciation = entity.getWordPronunciation();
        this.wordMeaning = entity.getWordMeaning();
        this.wordHits = entity.getWordHits();
        this.wordLike = entity.getWordLike();
    }
}