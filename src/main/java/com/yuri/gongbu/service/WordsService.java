package com.yuri.gongbu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;

import java.lang.IllegalArgumentException;

import com.yuri.gongbu.web.dto.WordsListResponseDto;
import com.yuri.gongbu.web.dto.WordAddRequestDto;
import com.yuri.gongbu.domain.words.WordsRepository;
import com.yuri.gongbu.global.GlobalVariable;
import com.yuri.gongbu.domain.words.Words;
import com.yuri.gongbu.web.dto.WordResponseDto;
import com.yuri.gongbu.web.dto.WordEditRequestDto;
import com.yuri.gongbu.web.dto.WordHomeResponseDto;

@RequiredArgsConstructor
@Service
public class WordsService {

    private final WordsRepository wordsRepository;

    public Map<String, Object> findByDeleteFlgZero(Pageable pageable) {
        Page<Words> result = wordsRepository.findByDeleteFlgOrderByWordIdDesc(GlobalVariable.FALSE, pageable);

        return convertWordsForPagination(result);
    }

    public Map<String, Object> getSortedWords(Pageable pageable) {
        Page<Words> result = wordsRepository.findByDeleteFlg(GlobalVariable.FALSE, pageable);

        return convertWordsForPagination(result);
    }

    public Map<String, Object> findByDeleteFlgAndWordNameLike(String keyWord, Pageable pageable) {
        Page<Words> result = wordsRepository.findByDeleteFlgAndWordNameContaining(GlobalVariable.FALSE, keyWord, pageable);

        return convertWordsForPagination(result);
    }

    @Transactional
    public void add(WordAddRequestDto wordAddRequestDto) {
        wordsRepository.save(wordAddRequestDto.toEntity());
    }

    @Transactional
    public void edit(WordEditRequestDto requestDto) {
        Words word = wordsRepository.findByWordId(requestDto.getWordId()).orElseThrow(() -> new IllegalArgumentException("該当することばがありません。"));
        word.update(requestDto.getWordName(), requestDto.getWordPronunciation(), requestDto.getWordMeaning(), requestDto.getWordExample());
    }

    @Transactional
    public void delete(Integer wordId) {
        Words word = wordsRepository.findByWordId(wordId).orElseThrow(() -> new IllegalArgumentException("該当することばがありません。"));
        word.delete();
    }
    
    public WordResponseDto findByWordId(Integer wordId) {
        Words word = wordsRepository.findByWordIdAndDeleteFlg(wordId, GlobalVariable.FALSE).orElseThrow(() -> new IllegalArgumentException("該当することばがありません。"));
        return new WordResponseDto(word);
    }

    @Transactional
    public void countUpWordHit(Integer wordId) {
        Words word = wordsRepository.findByWordIdAndDeleteFlg(wordId, GlobalVariable.FALSE).orElseThrow(() -> new IllegalArgumentException("該当することばがありません。"));
        word.countUpHits();
    }

    @Transactional
    public void countUpWordLike(Integer wordId) {
        Words word = wordsRepository.findByWordIdAndDeleteFlg(wordId, GlobalVariable.FALSE).orElseThrow(() -> new IllegalArgumentException("該当することばがありません。"));
        word.countUpLike();
    }

    public List<WordHomeResponseDto> rankTop10ByLike() {
        List<Words> result = wordsRepository.findTop10ByDeleteFlgOrderByWordLikeDesc(GlobalVariable.FALSE);
        List<WordHomeResponseDto> words = result.stream().map(WordHomeResponseDto::new).collect(Collectors.toList());

        return words;
    }

    public List<WordHomeResponseDto> rankTop10ByHits() {
        List<Words> result = wordsRepository.findTop10ByDeleteFlgOrderByWordHitsDesc(GlobalVariable.FALSE);
        List<WordHomeResponseDto> words = result.stream().map(WordHomeResponseDto::new).collect(Collectors.toList());

        return words;
    }

    public List<WordHomeResponseDto> getAllWords() {
        List<Words> result = wordsRepository.findByDeleteFlg(GlobalVariable.FALSE);
        List<WordHomeResponseDto> words = result.stream().map(WordHomeResponseDto::new).collect(Collectors.toList());

        return words;
    }

    public WordHomeResponseDto getFirstWords() {
        return new WordHomeResponseDto(wordsRepository.findTop1ByDeleteFlgOrderByWordIdAsc(GlobalVariable.FALSE));
    }

    public Map<String, Object> findMyWord(Integer userId, Pageable pageable) {
        Page<Words> result = wordsRepository.findByUserIdAndDeleteFlg(userId, GlobalVariable.FALSE, pageable);

        return convertWordsForPagination(result);
    }

    private Map<String, Object> convertWordsForPagination(Page<Words> result) {
        Map<String, Object> resultMap = new HashMap<>();
        List<WordsListResponseDto> words = result.stream().map(WordsListResponseDto::new).collect(Collectors.toList());

        resultMap.put("words", words);
        resultMap.put("isFirst", result.isFirst());
        resultMap.put("isLast", result.isLast());
        resultMap.put("hasNext", result.hasNext());
        resultMap.put("hasPrevious", result.hasPrevious());
        resultMap.put("getNumber", result.getNumber());
        resultMap.put("totalPages", result.getTotalPages());

        return resultMap;
    }
}