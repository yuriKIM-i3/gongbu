package com.yuri.gongbu.domain.words;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import com.yuri.gongbu.domain.words.WordsRepository;
import com.yuri.gongbu.domain.words.Words;
import com.yuri.gongbu.domain.user.UserRepository;
import com.yuri.gongbu.domain.user.User;
import com.yuri.gongbu.domain.user.Role;
import com.yuri.gongbu.global.GlobalVariable;

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

    @AfterAll
    static void cleanUp(@Autowired WordsRepository wordsRepository, @Autowired UserRepository userRepository) {
        wordsRepository.deleteAll();
        userRepository.deleteAll();
    }
}