package com.yuri.gongbu.web.dto;

import lombok.Getter;

import com.yuri.gongbu.domain.words.Words;

@Getter
public class WordHomeResponseDto {
    private Integer wordId;
    private String wordName;

    public WordHomeResponseDto(Words entity) {
        this.wordId = entity.getWordId();
        this.wordName = entity.getWordName();
    }
}