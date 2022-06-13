package com.yuri.gongbu.web.dto;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.yuri.gongbu.web.dto.WordEditRequestDto;
import com.yuri.gongbu.global.GlobalVariable;
import com.yuri.gongbu.domain.words.WordsRepository;
import com.yuri.gongbu.domain.words.Words;
import com.yuri.gongbu.domain.user.UserRepository;
import com.yuri.gongbu.domain.user.User;
import com.yuri.gongbu.domain.user.Role;

@SpringBootTest
public class WordEditRequestDtoTest {

    @Autowired
    Validator validator;

    @Autowired
    private WordsRepository wordsRepository;

    private WordEditRequestDto wordEditRequestDto = new WordEditRequestDto();
    private BindingResult bindingResult = new BindException(wordEditRequestDto, "wordEditRequestDto");

    @BeforeAll
    static void setup(@Autowired UserRepository userRepository, @Autowired WordsRepository wordsRepository) {
        userRepository.save(User.builder()
            .userName("yuriSAMA")
            .userEmail("y")
            .userRole(Role.MEMBER)    
            .deleteFlg(GlobalVariable.FALSE)
            .build());
        
        int userId = userRepository.findFirstByOrderByUserIdDesc().getUserId();
        String wordName = "update-word";
        String wordPronunciation = "this is for updating test word";
        String wordMeaning = "update-meaning";

        wordsRepository.save(Words.builder()
                                    .userId(userId)
                                    .wordName(wordName)
                                    .wordPronunciation(wordPronunciation)    
                                    .wordMeaning(wordMeaning)         
                                    .wordExample("did you update your test?")
                                    .wordHits(3)
                                    .wordLike(3)
                                    .deleteFlg(GlobalVariable.FALSE)
                                    .build());
    }

    @BeforeEach
    void getWordForUpdate() {
        Words word = wordsRepository.findAll().get(0);
        wordEditRequestDto.setWordName(word.getWordName());
        wordEditRequestDto.setWordPronunciation(word.getWordPronunciation());
        wordEditRequestDto.setWordMeaning(word.getWordMeaning());
        wordEditRequestDto.setWordExample(word.getWordExample());
    }

    @Test
    void wordNameInputNull() {
        // given
        wordEditRequestDto.setWordName(null);
        // when
        validator.validate(wordEditRequestDto, bindingResult);
        // then
        assertNotNull(bindingResult.getFieldError());
    }


    @Test
    void wordNameUptoMaxLength() throws Exception {
        // given
        String wordName =  new StringWriter(){{ for(int i=0;i<GlobalVariable.WORD_NAME_MAX_LENGTH;i++) write("a"); }}.toString();
        wordEditRequestDto.setWordName(wordName);
        // when
        validator.validate(wordEditRequestDto, bindingResult);
        // then
        assertNull(bindingResult.getFieldError());
    }

    @Test
    void wordNameOverMaxLength() throws Exception {
        // given
        String wordName =  new StringWriter(){{ for(int i=0;i<GlobalVariable.WORD_NAME_MAX_LENGTH + 1;i++) write("a"); }}.toString();
        wordEditRequestDto.setWordName(wordName);
        // when
        validator.validate(wordEditRequestDto, bindingResult);
        // then
        assertNotNull(bindingResult.getFieldError());
    }

    @Test
    void wordPronunciationInputNull() throws Exception {
        // given
        wordEditRequestDto.setWordPronunciation(null);
        // when
        validator.validate(wordEditRequestDto, bindingResult);
        // then
        assertNotNull(bindingResult.getFieldError());
    }

    @Test
    void wordPronunciationUptoMaxLength() throws Exception {
        // given
        String wordPronunciation =  new StringWriter(){{ for(int i=0;i<GlobalVariable.WORD_PRONUNCIATION_MAX_LENGTH;i++) write("a"); }}.toString();
        wordEditRequestDto.setWordPronunciation(wordPronunciation);
        // when
        validator.validate(wordEditRequestDto, bindingResult);
        // then
        assertNull(bindingResult.getFieldError());
    }

    @Test
    void wordPronunciationOverMaxLength() throws Exception {
        // given
        String wordPronunciation =  new StringWriter(){{ for(int i=0;i<GlobalVariable.WORD_PRONUNCIATION_MAX_LENGTH + 1;i++) write("a"); }}.toString();
        wordEditRequestDto.setWordPronunciation(wordPronunciation);
        // when
        validator.validate(wordEditRequestDto, bindingResult);
        // then
        assertNotNull(bindingResult.getFieldError());
    }

    @Test
    void wordMeaningInputNull() throws Exception {
        // given
        wordEditRequestDto.setWordMeaning(null);
        // when
        validator.validate(wordEditRequestDto, bindingResult);
        // then
        assertNotNull(bindingResult.getFieldError());
    }

    @Test
    void wordMeaningUptoMaxLength() throws Exception {
        // given
        String wordMeaning=  new StringWriter(){{ for(int i=0;i<GlobalVariable.WORD_MEANING_MAX_LENGTH;i++) write("a"); }}.toString();
        wordEditRequestDto.setWordMeaning(wordMeaning);
        // when
        validator.validate(wordEditRequestDto, bindingResult);
        // then
        assertNull(bindingResult.getFieldError());
    }

    @Test
    public void wordMeaningOverMaxLength() throws Exception {
        // given
        String wordMeaning=  new StringWriter(){{ for(int i=0;i<GlobalVariable.WORD_MEANING_MAX_LENGTH + 1;i++) write("a"); }}.toString();
        wordEditRequestDto.setWordMeaning(wordMeaning);
        // when
        validator.validate(wordEditRequestDto, bindingResult);
        // then
        assertNotNull(bindingResult.getFieldError());
    }

    @Test
    public void wordExampleUptoMaxLength() throws Exception {
        // given
        String wordExample=  new StringWriter(){{ for(int i=0;i<GlobalVariable.WORD_EXAMPLE_MAX_LENGTH;i++) write("a"); }}.toString();
        wordEditRequestDto.setWordExample(wordExample);
        // when
        validator.validate(wordEditRequestDto, bindingResult);
        // then
        assertNull(bindingResult.getFieldError());
    }

    @Test
    public void wordExampleOverMaxLength() throws Exception {
        // given
        String wordExample=  new StringWriter(){{ for(int i=0;i<GlobalVariable.WORD_EXAMPLE_MAX_LENGTH + 1;i++) write("a"); }}.toString();
        wordEditRequestDto.setWordExample(wordExample);
        // when
        validator.validate(wordEditRequestDto, bindingResult);
        // then
        assertNotNull(bindingResult.getFieldError());
    }

    @AfterAll
    static void cleanUp(@Autowired WordsRepository wordsRepository, @Autowired UserRepository userRepository) {
        wordsRepository.deleteAll();
        userRepository.deleteAll();
    }
}