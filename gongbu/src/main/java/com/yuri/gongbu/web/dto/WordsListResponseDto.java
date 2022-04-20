package com.yuri.gongbu.web.dto;

import lombok.Getter;

import java.time.LocalDateTime;

import com.yuri.gongbu.domain.words.Words;

@Getter
public class WordsListResponseDto {
    private Integer wordId;
    private String wordName;
    private String wordPronunciation;
    private String wordMeaning;
    private Integer wordHits;
    private Integer wordLike;
    private LocalDateTime createdAt;

    public WordsListResponseDto(Words entity) {
        this.wordId = entity.getWordId();
        this.wordName = entity.getWordName();
        this.wordPronunciation = entity.getWordPronunciation();
        this.wordMeaning = entity.getWordMeaning();
        this.wordHits = entity.getWordHits();
        this.wordLike = entity.getWordLike();
        this.createdAt = entity.getCreatedAt();
    }
}