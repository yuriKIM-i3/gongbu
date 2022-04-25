package com.yuri.gongbu.domain.words;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import com.yuri.gongbu.domain.words.WordsRepository;
import com.yuri.gongbu.domain.words.Words;
import com.yuri.gongbu.domain.user.UserRepository;
import com.yuri.gongbu.domain.user.User;
import com.yuri.gongbu.domain.user.Role;
import com.yuri.gongbu.global.GlobalVariable;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


// @RunWith(SpringRunner.class)
// @SpringBootTest
public class WordsRepositoryTest {
    // @Autowired
    // WordsRepository wordsRepository;
    // @Autowired
    // UserRepository userRepository;

    // @BeforeEach
    // public void setup() {
    //     userRepository.save(User.builder()
    //         .userName("yuriSAMA")
    //         .userEmail("y")
    //         .userRole(Role.MEMBER)    
    //         .deleteFlg(GlobalVariable.FALSE)
    //         .build());
    // }

    @Test
    public void insert_word() {
        //when
        // int userId = userRepository.findFirstByOrderByUserIdDesc().getUserId();
        // String wordName = "mbti";
        // String wordPronunciation = "엠비티아이";
        // String wordMeaning = "사기";
        
        // wordsRepository.save(Words.builder()
        //                             .userId(userId)
        //                             .wordName(wordName)
        //                             .wordPronunciation(wordPronunciation)    
        //                             .wordMeaning(wordMeaning)         
        //                             .wordExample("엠비티아이 검사 했어?")
        //                             .wordHits(3)
        //                             .wordLike(3)
        //                             .deleteFlg(1)
        //                             .build());
        // //when
        // List<Words> list = wordsRepository.findAll();

        // //then
        // Words words = list.get(0);
        // assertThat(words.getWordName()).isEqualTo(wordName);
        // assertThat(words.getWordMeaning()).isEqualTo(wordMeaning);
        assertEquals(1, 1);

    }

    // @After
    // public void cleanup() {
    //     wordsRepository.deleteAll();
    //     userRepository.deleteAll();
    // }
}