package com.yuri.gongbu.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class WordsListRequestDto {
    private Integer page;
    private String sortedBy;
    private String keyword;
    private String hiddenKeyword;
}
