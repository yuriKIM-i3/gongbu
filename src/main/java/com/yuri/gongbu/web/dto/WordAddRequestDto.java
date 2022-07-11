package com.yuri.gongbu.web.dto;

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
public class WordAddRequestDto {

    private Integer userId;

    @NotBlank
	@Size(max=GlobalVariable.WORD_NAME_MAX_LENGTH)
    private String wordName;

    @NotBlank
	@Size(max=GlobalVariable.WORD_PRONUNCIATION_MAX_LENGTH)
    private String wordPronunciation;

    @NotBlank
	@Size(max=GlobalVariable.WORD_MEANING_MAX_LENGTH)
    private String wordMeaning;

	@Size(max=GlobalVariable.WORD_EXAMPLE_MAX_LENGTH)
    private String wordExample;
    
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