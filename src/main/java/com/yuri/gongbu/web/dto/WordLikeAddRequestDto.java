package com.yuri.gongbu.web.dto;

import com.yuri.gongbu.domain.wordLike.WordLike;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

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
