package com.yuri.gongbu.service;

import com.yuri.gongbu.domain.wordLike.WordLikeRepository;
import com.yuri.gongbu.web.dto.WordLikeAddRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@RequiredArgsConstructor
@Service
public class WordLikeService {

    private final WordLikeRepository wordLikeRepository;

    public boolean isLikeButtonClicked(Integer userId, Integer wordId) {
        return Objects.nonNull(wordLikeRepository.findByUserIdAndWordId(userId, wordId));
    }

    public void add(WordLikeAddRequestDto wordLikeAddRequestDto) {
        wordLikeRepository.save(wordLikeAddRequestDto.toEntity());
    }
}
