package com.yuri.gongbu.web.dto;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import java.io.StringWriter;
import org.junit.jupiter.api.Disabled;

import com.yuri.gongbu.web.dto.WordAddRequestDto;
import com.yuri.gongbu.global.GlobalVariable;

@SpringBootTest
public class WordAddRequestDtoTest {

    @Autowired
    Validator validator;

    private WordAddRequestDto wordAddRequestDto = new WordAddRequestDto();
    private BindingResult bindingResult = new BindException(wordAddRequestDto, "wordAddRequestDto");

    @BeforeEach
    void setup() {
        wordAddRequestDto.setWordName("non-null");
        wordAddRequestDto.setWordPronunciation("non-null");
        wordAddRequestDto.setWordMeaning("non-null");
    }

    @Test
    void wordNameInputNull() {
        // given
        wordAddRequestDto.setWordName(null);
        // when
        validator.validate(wordAddRequestDto, bindingResult);
        // then
        assertNotNull(bindingResult.getFieldError());
    }


    // @Test
    // void wordNameUptoMaxLength() throws Exception {
    //     // given
    //     String wordName =  new StringWriter(){{ for(int i=0;i<GlobalVariable.WORD_NAME_MAX_LENGTH;i++) write("a"); }}.toString();
    //     wordAddRequestDto.setWordName(wordName);
    //     // when
    //     validator.validate(wordAddRequestDto, bindingResult);
    //     // then
    //     assertNull(bindingResult.getFieldError());
    // }

    // @Disabled
    // @Test
    // void wordNameOverMaxLength() throws Exception {
    //     // given
    //     String wordName =  new StringWriter(){{ for(int i=0;i<GlobalVariable.WORD_NAME_MAX_LENGTH + 1;i++) write("a"); }}.toString();
    //     wordAddRequestDto.setWordName(wordName);
    //     // when
    //     validator.validate(wordAddRequestDto, bindingResult);
    //     // then
    //     assertNotNull(bindingResult.getFieldError());
    // }

    // @Disabled
    // @Test
    // void wordPronunciationInputNull() throws Exception {
    //     // given
    //     wordAddRequestDto.setWordPronunciation(null);
    //     // when
    //     validator.validate(wordAddRequestDto, bindingResult);
    //     // then
    //     assertNotNull(bindingResult.getFieldError());
    // }

    // @Disabled
    // @Test
    // void wordPronunciationUptoMaxLength() throws Exception {
    //     // given
    //     String wordPronunciation =  new StringWriter(){{ for(int i=0;i<GlobalVariable.WORD_PRONUNCIATION_MAX_LENGTH;i++) write("a"); }}.toString();
    //     wordAddRequestDto.setWordPronunciation(wordPronunciation);
    //     // when
    //     validator.validate(wordAddRequestDto, bindingResult);
    //     // then
    //     assertNull(bindingResult.getFieldError());
    // }

    // @Disabled
    // @Test
    // void wordPronunciationOverMaxLength() throws Exception {
    //     // given
    //     String wordPronunciation =  new StringWriter(){{ for(int i=0;i<GlobalVariable.WORD_PRONUNCIATION_MAX_LENGTH + 1;i++) write("a"); }}.toString();
    //     wordAddRequestDto.setWordPronunciation(wordPronunciation);
    //     // when
    //     validator.validate(wordAddRequestDto, bindingResult);
    //     // then
    //     assertNotNull(bindingResult.getFieldError());
    // }

    // @Disabled
    // @Test
    // void wordMeaningInputNull() throws Exception {
    //     // given
    //     wordAddRequestDto.setWordMeaning(null);
    //     // when
    //     validator.validate(wordAddRequestDto, bindingResult);
    //     // then
    //     assertNotNull(bindingResult.getFieldError());
    // }

    // @Disabled
    // @Test
    // void wordMeaningUptoMaxLength() throws Exception {
    //     // given
    //     String wordMeaning=  new StringWriter(){{ for(int i=0;i<GlobalVariable.WORD_MEANING_MAX_LENGTH;i++) write("a"); }}.toString();
    //     wordAddRequestDto.setWordMeaning(wordMeaning);
    //     // when
    //     validator.validate(wordAddRequestDto, bindingResult);
    //     // then
    //     assertNull(bindingResult.getFieldError());
    // }

    // @Disabled
    // @Test
    // public void wordMeaningOverMaxLength() throws Exception {
    //     // given
    //     String wordMeaning=  new StringWriter(){{ for(int i=0;i<GlobalVariable.WORD_MEANING_MAX_LENGTH + 1;i++) write("a"); }}.toString();
    //     wordAddRequestDto.setWordMeaning(wordMeaning);
    //     // when
    //     validator.validate(wordAddRequestDto, bindingResult);
    //     // then
    //     assertNotNull(bindingResult.getFieldError());
    // }

    // @Disabled
    // @Test
    // public void wordExampleUptoMaxLength() throws Exception {
    //     // given
    //     String wordExample=  new StringWriter(){{ for(int i=0;i<GlobalVariable.WORD_EXAMPLE_MAX_LENGTH;i++) write("a"); }}.toString();
    //     wordAddRequestDto.setWordExample(wordExample);
    //     // when
    //     validator.validate(wordAddRequestDto, bindingResult);
    //     // then
    //     assertNull(bindingResult.getFieldError());
    // }

    // @Disabled
    // @Test
    // public void wordExampleOverMaxLength() throws Exception {
    //     // given
    //     String wordExample=  new StringWriter(){{ for(int i=0;i<GlobalVariable.WORD_EXAMPLE_MAX_LENGTH + 1;i++) write("a"); }}.toString();
    //     wordAddRequestDto.setWordExample(wordExample);
    //     // when
    //     validator.validate(wordAddRequestDto, bindingResult);
    //     // then
    //     assertNotNull(bindingResult.getFieldError());
    // }
}