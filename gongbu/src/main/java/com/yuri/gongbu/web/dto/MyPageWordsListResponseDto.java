package com.yuri.gongbu.web.dto;

import java.time.LocalDateTime;

import lombok.Getter;

import com.yuri.gongbu.domain.words.Words;

@Getter
public class MyPageWordsListResponseDto {
    private Integer wordId;
    private String wordName;
    private Integer wordHits;
    private Integer wordLike;
    private LocalDateTime createdAt;

    public MyPageWordsListResponseDto(Words entity) {
        this.wordId = entity.getWordId();
        this.wordName = entity.getWordName();
        this.wordHits = entity.getWordHits();
        this.wordLike = entity.getWordLike();
        this.createdAt = entity.getCreatedAt();
    }
}