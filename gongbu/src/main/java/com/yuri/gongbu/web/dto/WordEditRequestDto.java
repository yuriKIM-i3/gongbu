package com.yuri.gongbu.web.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class WordEditRequestDto {

    private Integer wordId;
    private Integer userId;

    @NotBlank
	@Size(max=50)
    private String wordName;

    @NotBlank
	@Size(max=100)
    private String wordPronunciation;

    @NotBlank
	@Size(max=255)
    private String wordMeaning;

	@Size(max=500)
    private String wordExample;
    
}