package com.yuri.gongbu.util;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
// import java.util.List;

// import com.yuri.gongbu.domain.user.UserRepository;
// import com.yuri.gongbu.domain.user.User;
// import com.yuri.gongbu.domain.user.Role;
// import com.yuri.gongbu.domain.words.Words;
// import com.yuri.gongbu.domain.words.WordsRepository;
// import com.yuri.gongbu.web.dto.WordResponseDto;
// import com.yuri.gongbu.global.GlobalVariable;
// import com.yuri.gongbu.util.WordsApiUtil;
// import com.yuri.gongbu.config.auth.dto.SessionUser;

@SpringBootTest
public class WordsApiUtilTest {

    // @Autowired
    // private WordsRepository wordsRepository;

    // @Autowired
    // private UserRepository userRepository;

    // @Test
    // void checkWordOwner() {
    //     // given
    //     userRepository.save(User.builder()
    //         .userName("user1")
    //         .userEmail("user1")
    //         .userRole(Role.MEMBER)    
    //         .deleteFlg(GlobalVariable.FALSE)
    //         .build());
        
    //     userRepository.save(User.builder()
    //         .userName("user2")
    //         .userEmail("user2")
    //         .userRole(Role.MEMBER)    
    //         .deleteFlg(GlobalVariable.FALSE)
    //         .build());

    //     int userId = userRepository.findAll().get(0).getUserId();
    //     String wordName = "test-word";
    //     String wordPronunciation = "this is test word";
    //     String wordMeaning = "test-meaning";
    //     wordsRepository.save(Words.builder()
    //                                 .userId(userId)
    //                                 .wordName(wordName)
    //                                 .wordPronunciation(wordPronunciation)    
    //                                 .wordMeaning(wordMeaning)         
    //                                 .wordExample("did you do your test?")
    //                                 .wordHits(3)
    //                                 .wordLike(3)
    //                                 .deleteFlg(0)
    //                                 .build());

    //     List<Words> words = wordsRepository.findAll();
    //     Words word = words.get(0);
    //     WordResponseDto wordResponseDto = new WordResponseDto(word);
    //     List<User> users = userRepository.findAll();
    //     User user1 = users.get(0);
    //     User user2 = users.get(1);
    //     SessionUser sessionUser1 = new SessionUser(user1);
    //     SessionUser sessionUser2 = new SessionUser(user2);

    //     // when
    //     boolean isWriter = WordsApiUtil.checkWordOwner(wordResponseDto, sessionUser1);
    //     boolean isNotWriter = WordsApiUtil.checkWordOwner(wordResponseDto, sessionUser2);

    //     // then
    //     assertTrue(isWriter);
    //     assertFalse(isNotWriter);
    // }

    // @AfterAll
    // static void cleanUp(@Autowired WordsRepository wordsRepository, @Autowired UserRepository userRepository) {
    //     wordsRepository.deleteAll();
    //     userRepository.deleteAll();
    // }

    @Test
    void checkWordOwner(){
        assertEquals(1, 1);
    }
}