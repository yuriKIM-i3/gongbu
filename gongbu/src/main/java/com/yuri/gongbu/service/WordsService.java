package com.yuri.gongbu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.yuri.gongbu.web.dto.WordsListResponseDto;
import com.yuri.gongbu.domain.words.WordsRepository;
import com.yuri.gongbu.global.GlobalVariable;

@RequiredArgsConstructor
@Service
public class WordsService {

    private final WordsRepository wordsRepository;

    @Transactional(readOnly = true)
    public List<WordsListResponseDto> findAll() {
        return wordsRepository.findAll().stream()
                .map(WordsListResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<WordsListResponseDto> findByDeleteFlgZero() {
        return wordsRepository.findByDeleteFlg(GlobalVariable.DELETE_FLG_0).stream()
                .map(WordsListResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<WordsListResponseDto> getWordsSortedBy(String sortedBy) {
        Sort sort = sortBy(sortedBy);
        return wordsRepository.findByDeleteFlg(GlobalVariable.DELETE_FLG_0, sort).stream()
                .map(WordsListResponseDto::new)
                .collect(Collectors.toList());
    }

    private Sort sortBy(String sortedBy) {
        return Sort.by(Sort.Direction.DESC, sortedBy);
    }
}