package com.yuri.gongbu.web.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

import com.yuri.gongbu.domain.wordLike.WordLike;

@AllArgsConstructor 
@Getter
@Setter
public class WordLikeAddRequestDto {

    private Integer userId;
    private Integer wordId;
    
    public WordLike toEntity() {
        return WordLike.builder()
                    .userId(userId)
                    .wordId(wordId)
                    .build();
    }
}