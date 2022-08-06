package com.yuri.gongbu.domain.words;

import com.yuri.gongbu.domain.user.Role;
import com.yuri.gongbu.domain.user.User;
import com.yuri.gongbu.domain.user.UserRepository;
import com.yuri.gongbu.domain.words.Words;
import com.yuri.gongbu.domain.words.WordsRepository;
import com.yuri.gongbu.global.GlobalVariable;
import com.yuri.gongbu.web.dto.WordsListResponseDto;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WordsRepositoryTest {

    @Autowired
    private WordsRepository wordsRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeAll
    static void setup(@Autowired UserRepository userRepository) {
        userRepository.save(User.builder()
                .userName("yuriSAMA")
                .userEmail("y")
                .userRole(Role.MEMBER)
                .deleteFlg(GlobalVariable.FALSE)
                .build());
    }

    @BeforeEach
    void insertWord() {
        int userId = userRepository.findFirstByOrderByUserIdDesc().getUserId();
        String wordName = "test-word";
        String wordPronunciation = "this is test word";
        String wordMeaning = "test-meaning";

        wordsRepository.save(Words.builder()
                .userId(userId)
                .wordName(wordName)
                .wordPronunciation(wordPronunciation)
                .wordMeaning(wordMeaning)
                .wordExample("did you do your test?")
                .wordHits(3)
                .wordLike(3)
                .deleteFlg(0)
                .build());
    }

    @Test
    public void hasInsertedWord() {
        //when
        String wordName = "test-word";
        String wordPronunciation = "this is test word";
        String wordMeaning = "test-meaning";
        List<Words> list = wordsRepository.findAll();

        //then
        Words word = list.get(0);
        assertEquals(word.getWordName(), wordName);
        assertEquals(word.getWordMeaning(), wordMeaning);
    }

    @Test
    public void updateWord() {
        //given
        String wordName = "test-word-update";
        String wordPronunciation = "test-pronunciation-update";
        String wordMeaning = "test-meaning-update";
        String wordExample = "did you update your test code?";

        List<Words> originalWordList = wordsRepository.findAll();
        Words word = originalWordList.get(0);

        //when
        word.update(wordName, wordPronunciation, wordMeaning, wordExample);
        List<Words> editedWordList = wordsRepository.findAll();
        word = editedWordList.get(0);

        //then
        assertEquals(word.getWordName(), wordName);
        assertEquals(word.getWordPronunciation(), wordPronunciation);
        assertEquals(word.getWordMeaning(), wordMeaning);
        assertEquals(word.getWordExample(), wordExample);
    }

    @Test
    public void deleteWord() {
        //given
        List<Words> originalWordList = wordsRepository.findAll();
        Words word = originalWordList.get(0);

        //when
        word.delete();

        //then
        assertEquals(word.getDeleteFlg(), GlobalVariable.TRUE);
    }

    @Test
    public void searchWord() {
        //given
        String keyWord = "word";
        PageRequest pageable = PageRequest.of(0, GlobalVariable.WORD_PAGE_SIZE);

        //when
        Page<Words> result = wordsRepository.findByDeleteFlgAndWordNameContaining(GlobalVariable.FALSE, keyWord, pageable);
        List<WordsListResponseDto> wordList = result.stream().map(WordsListResponseDto::new).collect(Collectors.toList());

        //then
        assertTrue(wordList.get(0).getWordName().contains(keyWord));
    }

    @Test
    public void countUpHits() {
        //given
        List<Words> wordList = wordsRepository.findAll();
        Words words = wordList.get(0);
        Integer beforeCount = words.getWordHits();

        //when
        words.countUpHits();

        //then
        Words result = wordsRepository.findAll().get(0);
        assertEquals(result.getWordHits(), beforeCount + 1);
    }

    @Test
    public void countUpLike() {
        //given
        List<Words> wordList = wordsRepository.findAll();
        Words words = wordList.get(0);
        Integer beforeCount = words.getWordLike();

        //when
        words.countUpLike();

        //then
        Words result = wordsRepository.findAll().get(0);
        assertEquals(result.getWordLike(), beforeCount + 1);
    }

    @AfterAll
    static void cleanUp(@Autowired WordsRepository wordsRepository, @Autowired UserRepository userRepository) {
        wordsRepository.deleteAll();
        userRepository.deleteAll();
    }
}
