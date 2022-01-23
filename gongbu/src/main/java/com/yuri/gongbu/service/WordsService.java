package com.yuri.gongbu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.yuri.gongbu.web.dto.WordsListResponseDto;
import com.yuri.gongbu.domain.words.WordsRepository;

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

}