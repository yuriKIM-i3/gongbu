package com.yuri.gongbu.domain.words;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import org.springframework.boot.test.context.SpringBootTest;

import com.yuri.gongbu.domain.words.WordsRepository;
import com.yuri.gongbu.domain.words.Words;
import com.yuri.gongbu.domain.user.UserRepository;
import com.yuri.gongbu.domain.user.User;
import com.yuri.gongbu.domain.user.Role;
import com.yuri.gongbu.global.GlobalVariable;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WordsRepositoryTest {
    // @Autowired
    
    // @Autowired


    private final WordsRepository wordsRepository;
    private final UserRepository userRepository;

    @Autowired
    WordsRepositoryTest(WordsRepository wordsRepository, UserRepository userRepository) {
        this.wordsRepository = wordsRepository;
        this.userRepository = userRepository;
    }

    @BeforeEach
    public void setup() {
        userRepository.save(User.builder()
            .userName("yuriSAMA")
            .userEmail("y")
            .userRole(Role.MEMBER)    
            .deleteFlg(GlobalVariable.FALSE)
            .build());
    }

    @Test
    public void insert_word() {
        //when
        int userId = userRepository.findFirstByOrderByUserIdDesc().getUserId();
        String wordName = "mbti";
        String wordPronunciation = "엠비티아이";
        String wordMeaning = "사기";
        
        wordsRepository.save(Words.builder()
                                    .userId(userId)
                                    .wordName(wordName)
                                    .wordPronunciation(wordPronunciation)    
                                    .wordMeaning(wordMeaning)         
                                    .wordExample("엠비티아이 검사 했어?")
                                    .wordHits(3)
                                    .wordLike(3)
                                    .deleteFlg(1)
                                    .build());
        //when
        List<Words> list = wordsRepository.findAll();

        //then
        Words words = list.get(0);
        assertEquals(words.getWordName(), wordName);
        assertEquals(words.getWordMeaning(), wordMeaning);
    }

    @AfterEach
    public void cleanup() {
        wordsRepository.deleteAll();
        userRepository.deleteAll();
    }
}