package com.yuri.gongbu.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.yuri.gongbu.global.GlobalVariable;

@Getter
@Setter
public class WordEditRequestDto {

    private Integer wordId;
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
}